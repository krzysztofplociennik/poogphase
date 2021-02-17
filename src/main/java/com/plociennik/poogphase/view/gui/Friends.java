package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.FriendListForm;
import com.plociennik.poogphase.view.gui.forms.PossibleFriendForm;
import com.plociennik.poogphase.view.logic.FriendsManager;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Route("friends")
@PageTitle("Friends")
public class Friends extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout friendsPageContentLayout;
    private ApiClient apiClient;

    @Autowired
    public Friends(ApiClient apiClient) {
        this.apiClient = apiClient;
        setSizeFull();
        setupFriendsPageContentView();
        setupFriendsAndMaybeFriends();

//        add(generalView);
    }

    public void setupFriendsPageContentView() {
        this.generalView = new GeneralView();
        this.friendsPageContentLayout = new SplitLayout();
//        generalView.setSizeFull();

        friendsPageContentLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        friendsPageContentLayout.setPrimaryStyle("minWidth", "750px");
        friendsPageContentLayout.setPrimaryStyle("maxWidth", "750px");
        generalView.addToSecondary(friendsPageContentLayout);
        add(generalView);
    }

    public void setupFriendsAndMaybeFriends() {
        FriendsManager friendsManager = new FriendsManager(this.apiClient);
        Optional<UserDto> loggedUser = apiClient.getUsers().stream()
                        .filter(userDto1 -> userDto1.getUsername().equals("dummy")).findAny();

        VerticalLayout friendsLayout = new VerticalLayout();
        friendsPageContentLayout.addToPrimary(friendsLayout);
        friendsLayout.add(new H2("Your friends"));

        for (UserDto user : friendsManager.searchFriends(loggedUser.get())) {
            friendsLayout.add(new FriendListForm(user));
        }

        VerticalLayout maybeFriendsLayout = new VerticalLayout();
        friendsPageContentLayout.addToSecondary(maybeFriendsLayout);
        maybeFriendsLayout.add(new H2("Do you know these people?"));

        for (UserDto user : friendsManager.searchPossibleFriends(loggedUser.get())) {
            maybeFriendsLayout.add(new PossibleFriendForm(user));
        }
    }
}

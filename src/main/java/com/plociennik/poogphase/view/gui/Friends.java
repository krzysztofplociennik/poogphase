package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.FriendListForm;
import com.plociennik.poogphase.view.gui.forms.PossibleFriendForm;
import com.plociennik.poogphase.view.logic.FriendsManager;
import com.plociennik.poogphase.view.logic.SessionManager;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

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
    }

    public void setupFriendsPageContentView() {
        this.generalView = new GeneralView();
        this.friendsPageContentLayout = new SplitLayout();

        friendsPageContentLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        friendsPageContentLayout.setPrimaryStyle("minWidth", "750px");
        friendsPageContentLayout.setPrimaryStyle("maxWidth", "750px");
        generalView.addToSecondary(friendsPageContentLayout);
        add(generalView);
    }

    public void setupFriendsAndMaybeFriends() {
        FriendsManager friendsManager = new FriendsManager(this.apiClient);
        SessionManager sessionManager = new SessionManager(this.apiClient);

        VerticalLayout friendsLayout = new VerticalLayout();
        friendsPageContentLayout.addToPrimary(friendsLayout);
        friendsLayout.add(new H2("Your friends"));

        for (UserDto user : friendsManager.searchFriends(sessionManager.getLoggedUser())) {
            friendsLayout.add(new FriendListForm(user));
        }

        VerticalLayout maybeFriendsLayout = new VerticalLayout();
        friendsPageContentLayout.addToSecondary(maybeFriendsLayout);
        maybeFriendsLayout.add(new H2("Do you know these people?"));

        for (UserDto user : friendsManager.searchPossibleFriends(sessionManager.getLoggedUser())) {
            maybeFriendsLayout.add(new PossibleFriendForm(user));
        }
    }
}
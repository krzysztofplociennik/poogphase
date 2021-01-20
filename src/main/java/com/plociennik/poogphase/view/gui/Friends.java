package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.FriendListForm;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("friends")
@PageTitle("Friends")
public class Friends extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout splitLayout;
    private ApiClient apiClient;

    @Autowired
    public Friends(ApiClient apiClient) {
        this.apiClient = apiClient;
        setSizeFull();
        setupGeneralView();
        setupFriendsAndMaybeFriends();

        add(generalView);
    }

    public void setupGeneralView() {
        this.generalView = new GeneralView();
        this.splitLayout = new SplitLayout();
        generalView.setSizeFull();

        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        splitLayout.setPrimaryStyle("minWidth", "750px");
        splitLayout.setPrimaryStyle("maxWidth", "750px");
        splitLayout.addToSecondary(new Button("People that you can know"));
        generalView.addToSecondary(splitLayout);
        add(generalView);
    }

    public void setupFriendsAndMaybeFriends() {
        List<UserDto> allUsers = apiClient.getUsers();

        VerticalLayout friendsLayout = new VerticalLayout();
        splitLayout.addToPrimary(friendsLayout);
        friendsLayout.add(new H2("Your friends"));

        for (UserDto user : allUsers) {
            friendsLayout.add(new FriendListForm(user));
        }

        VerticalLayout maybeFriendsLayout = new VerticalLayout();
        splitLayout.addToSecondary(maybeFriendsLayout);
        maybeFriendsLayout.add(new H2("Do you know these people?"));
    }
}

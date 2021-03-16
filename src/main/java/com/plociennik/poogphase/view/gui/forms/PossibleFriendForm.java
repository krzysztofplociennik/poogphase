package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.logic.SessionManager;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Session;
import java.io.IOException;

public class PossibleFriendForm extends FormLayout {
    private ApiClient apiClient;
    private SessionManager sessionManager;

    @Autowired
    public PossibleFriendForm(ApiClient apiClient, UserDto possibleFriend) {
        this.apiClient = apiClient;
        this.sessionManager = new SessionManager(this.apiClient);
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        Paragraph username = new Paragraph(possibleFriend.getUsername());
        Button friendInviteButton = new Button("add friend");
        friendInviteButton.addClickListener(buttonClickEvent -> {
            try {
                addFriend(sessionManager.getLoggedUser(), possibleFriend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Button sendMessageButton = new Button("message");
        Text text = new Text("============================");

        buttonsLayout.add(friendInviteButton, sendMessageButton);
        verticalLayout.add(username, buttonsLayout, text);
        add(verticalLayout);
    }

    public void addFriend(UserDto user, UserDto friendToBe) throws IOException {
        System.out.println("User: " + user.getFriends().size());
        System.out.println("Possible friend: " + friendToBe.getFriends().size());
        user.getFriends().add(friendToBe.getUsername());
        friendToBe.getFriends().add(user.getUsername());
        System.out.println("User: " + user.getFriends().size());
        System.out.println("Possible friend: " + friendToBe.getFriends().size());
        apiClient.updateUser(user);
        apiClient.updateUser(friendToBe);

        System.out.println("User after adding: " + apiClient.getUsers().stream()
                .filter(userDto -> userDto.getUsername().equals(user.getUsername()))
                .findAny()
                .get().getFriends().size());
        System.out.println("Possible friend after adding: " + apiClient.getUsers().stream()
                .filter(userDto -> userDto.getUsername().equals(friendToBe.getUsername()))
                .findAny()
                .get().getFriends().size());
    }
}

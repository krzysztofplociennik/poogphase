package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.logic.SessionManager;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public class FriendListForm extends FormLayout {
    private ApiClient apiClient;

    @Autowired
    public FriendListForm(ApiClient apiClient, UserDto userDto) {
        this.apiClient = apiClient;
        VerticalLayout verticalLayout = new VerticalLayout();
        SessionManager sessionManager = new SessionManager(this.apiClient);
        Paragraph username = new Paragraph(userDto.getUsername());
        Button unfriendButton = new Button("unfriend", buttonClickEvent -> {
            try {
                unfriendUser(sessionManager.getLoggedUser(), userDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Text text = new Text("============================");

        verticalLayout.add(username, unfriendButton, text);
        add(verticalLayout);
    }

    public void unfriendUser(UserDto userDto, UserDto friend) throws IOException {
        userDto.getFriends().remove(friend.getUsername());
        apiClient.updateUser(userDto);
        friend.getFriends().remove(userDto.getUsername());
        apiClient.updateUser(friend);
    }
}

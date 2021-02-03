package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.UserDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PossibleFriendForm extends FormLayout {

    public PossibleFriendForm(UserDto userDto) {
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        Paragraph username = new Paragraph(userDto.getUsername());
        Button friendInviteButton = new Button("friend invite");
        Button sendMessageButton = new Button("message");
        Text text = new Text("============================");

        buttonsLayout.add(friendInviteButton, sendMessageButton);
        verticalLayout.add(username, buttonsLayout, text);
        add(verticalLayout);
    }
}

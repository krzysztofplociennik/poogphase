package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.UserDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class FriendListForm extends FormLayout {

    public FriendListForm(UserDto userDto) {
        VerticalLayout verticalLayout = new VerticalLayout();
        Paragraph username = new Paragraph(userDto.getUsername());
        Button unfriendButton = new Button("unfriend");
        Text text = new Text("============================");

        verticalLayout.add(username, unfriendButton, text);
        add(verticalLayout);
    }
}

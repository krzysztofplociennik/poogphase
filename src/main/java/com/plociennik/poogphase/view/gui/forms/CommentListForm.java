package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentListForm extends FormLayout {
    private ApiClient apiClient;

    @Autowired
    public CommentListForm(ApiClient apiClient, CommentDto comment) {
        this.apiClient = apiClient;

        Paragraph details = new Paragraph(apiClient.getUsers().stream()
                .filter(userDto -> userDto.getId() == comment.getAuthorId())
                .findAny()
                .get()
                .getUsername() +
                ", " + comment.getDateTime().toString());
        Paragraph content = new Paragraph(comment.getContent());
        Paragraph separator = new Paragraph("==================");

        VerticalLayout verticalLayout = new VerticalLayout(details, content, separator);
        add(verticalLayout);
    }

}

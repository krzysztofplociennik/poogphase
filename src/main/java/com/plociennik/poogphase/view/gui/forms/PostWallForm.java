package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class PostWallForm extends FormLayout {

    private ApiClient apiClient;

    @Autowired
    public PostWallForm(ApiClient apiClient, PostDto post) {
        this.apiClient = apiClient;
        VerticalLayout layout = new VerticalLayout();
        Paragraph details = new Paragraph(
                apiClient.getUsers().stream()
                        .filter(userDto -> userDto.getId() == post.getAuthorId())
                        .findAny()
                        .get()
                        .getUsername() +
                        ", " + howLongAgo(post.getDateTime()));
        Paragraph content = new Paragraph(post.getContent());
        Paragraph comments = new Paragraph(post.getComments().size() + " comments");
        layout.add(details, content, comments);
        add(layout);
    }

    public String howLongAgo(LocalDateTime dateTime) {
        LocalDateTime currentTime = LocalDateTime.now();

        if (dateTime.getYear() != currentTime.getYear()) {
            return dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " + dateTime.getYear();
        } else if (dateTime.getMonth() != currentTime.getMonth()) {
            return dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        } else if (dateTime.getDayOfMonth() != currentTime.getDayOfMonth()) {
            return dateTime.getDayOfMonth() + " " + dateTime.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        } else {
            return dateTime.getHour() + ":" + dateTime.getMinute();
        }
    }
}

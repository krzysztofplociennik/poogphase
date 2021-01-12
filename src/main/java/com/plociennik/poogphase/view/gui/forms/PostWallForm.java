package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.Post;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.Locale;

public class PostWallForm extends FormLayout {

    @Autowired
    public PostWallForm(Post post) {
        VerticalLayout layout = new VerticalLayout();
        Paragraph details = new Paragraph(post.getAuthor().getUsername() + ", " + howLongAgo(post.getDateTime()));
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

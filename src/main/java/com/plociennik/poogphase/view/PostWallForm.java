package com.plociennik.poogphase.view;

import com.plociennik.poogphase.model.Post;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class PostWallForm extends FormLayout {
    private Dash dash;

    @Autowired
    public PostWallForm(Post post) {
        VerticalLayout layout = new VerticalLayout();
        Paragraph first = new Paragraph(post.getAuthor().getUsername());
        Paragraph second = new Paragraph(String.valueOf(post.getId()));
        Paragraph third = new Paragraph(post.getDateTime().toString());
        Paragraph forth = new Paragraph(post.getContent());
        Paragraph fifth = new Paragraph(String.valueOf(post.getComments().size()));
        layout.add(first, second, third, forth, fifth);
        add(layout);
    }
}

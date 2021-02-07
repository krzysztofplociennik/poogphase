package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.logic.CommentsSidePage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class PostWallForm extends FormLayout {

    private ApiClient apiClient;

    @Autowired
    public PostWallForm(ApiClient apiClient, PostDto post, SplitLayout sidePage, Button cancelButton) {
        this.apiClient = apiClient;
        VerticalLayout postLayout = new VerticalLayout();
        HorizontalLayout horizontalLayoutForComments = new HorizontalLayout();
        Paragraph details = new Paragraph(
                apiClient.getUsers().stream()
                        .filter(userDto -> userDto.getId() == post.getAuthorId())
                        .findAny()
                        .get()
                        .getUsername() +
                        ", " + howLongAgo(post.getDateTime()));
        Paragraph content = new Paragraph(post.getContent());
        TextArea commentPostTextArea = new TextArea("comment here!");
        commentPostTextArea.setWidth("350px");
        commentPostTextArea.setHeight("100px");
        Button commentButton = new Button("comment");

        HorizontalLayout commentHorizontal = new HorizontalLayout();
        ComboBox<UserDto> userPick = new ComboBox<>();
        userPick.setItems(apiClient.getUsers());
        userPick.setItemLabelGenerator(UserDto::getUsername);
        commentHorizontal.add(commentButton, userPick);

        commentButton.addClickListener(buttonClickEvent -> {
            try {
                createComment(userPick.getValue().getId(), post.getId(), commentPostTextArea.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Paragraph numberOfComments = new Paragraph(post.getComments().size() + " comments");
        Button showCommentsButton = new Button("show comments");
        showCommentsButton.addClickListener(buttonClickEvent -> showComments(sidePage, post, cancelButton));

        if (post.getComments().size() == 0) {
            showCommentsButton.setVisible(false);
        }
        horizontalLayoutForComments.add(numberOfComments, showCommentsButton);
        postLayout.add(details, content, commentPostTextArea, commentHorizontal, horizontalLayoutForComments);
        add(postLayout);
    }

    public void createComment(long authorId, long postId, String content) throws IOException {
        CommentDto comment = new CommentDto();
        comment.setAuthorId(authorId);
        comment.setPostId(postId);
        comment.setDateTime(LocalDateTime.now());
        comment.setContent(content);

        apiClient.createComment(comment);
    }

    public void showComments(SplitLayout layoutToChange, PostDto postToShow, Button cancelButton) {
        layoutToChange.addToSecondary(new CommentsSidePage(this.apiClient, postToShow, layoutToChange, cancelButton));
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

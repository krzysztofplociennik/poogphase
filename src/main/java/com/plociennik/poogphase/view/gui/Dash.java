package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.PostWallForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.splitlayout.SplitLayoutVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Route("dash")
@PageTitle("Dashboard")
@UIScope
public class Dash extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout firstPartOfContentLayout;
    private SplitLayout secondPartOfContentLayout;
    private ApiClient apiClient;

    @Autowired
    public Dash(ApiClient apiClient) {
        this.apiClient = apiClient;
        setSizeFull();
        setupContentView();

        add(generalView);
    }

    public void setupContentView() {
        this.generalView = new GeneralView();
        this.firstPartOfContentLayout = new SplitLayout();
        this.secondPartOfContentLayout = new SplitLayout();
        generalView.setSizeFull();
        generalView.addToSecondary(firstPartOfContentLayout);
        firstPartOfContentLayout.addToSecondary(secondPartOfContentLayout);

        setupPostActionView();
        setupWallView();
        setupAPIView();
    }

    public void setupPostActionView() {
        firstPartOfContentLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        firstPartOfContentLayout.setPrimaryStyle("minWidth", "300px");
        firstPartOfContentLayout.setPrimaryStyle("maxWidth", "300px");

        TextArea whatDoYouThinkTextArea = new TextArea("Want to share something?");
        whatDoYouThinkTextArea.setWidth("200px");
        whatDoYouThinkTextArea.setHeight("150px");
        whatDoYouThinkTextArea.setPreventInvalidInput(true);
        Button createPostButton = new Button("post!");
        createPostButton.addClickListener(buttonClickEvent -> {
            PostDto post = new PostDto();
            post.setAuthorId(apiClient.getUsers().stream()
                            .filter(userDto -> userDto.getUsername().equals("dummy"))
                            .findAny()
                            .get()
                            .getId());
            post.setDateTime(LocalDateTime.now());
            post.setContent(whatDoYouThinkTextArea.getValue());
            try {
                apiClient.createPost(post);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(whatDoYouThinkTextArea, createPostButton);
        firstPartOfContentLayout.addToPrimary(verticalLayout);
    }

    public void setupWallView() {
        secondPartOfContentLayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        secondPartOfContentLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        secondPartOfContentLayout.setPrimaryStyle("minWidth", "950px");
        secondPartOfContentLayout.setPrimaryStyle("maxWidth", "950px");

        List<PostDto> posts = apiClient.getPosts();
        Comparator<PostDto> compareByDate = Comparator.comparing(PostDto::getDateTime).reversed();
        posts.sort(compareByDate);

        VerticalLayout verticalLayout = new VerticalLayout();
        secondPartOfContentLayout.addToPrimary(verticalLayout);
        for (PostDto post : posts) {
            verticalLayout.add(new PostWallForm(this.apiClient, post), new Paragraph("================="));
        }
    }

    public void setupAPIView() {
        secondPartOfContentLayout.addToSecondary(new Button("API WIDGETS"));
    }

}

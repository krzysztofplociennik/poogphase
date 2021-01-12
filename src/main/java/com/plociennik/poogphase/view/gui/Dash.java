package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.service.PostService;
import com.plociennik.poogphase.service.UserService;
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
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Route
@Component
@UIScope
public class Dash extends HorizontalLayout {
    private final PostService postService;
    private final UserService userService;

    SplitLayout generalView = new SplitLayout();
    SplitLayout topLayout = new SplitLayout();
    SplitLayout bottomLayout = new SplitLayout();
    SplitLayout secondBottomLayout = new SplitLayout();


    @Autowired
    public Dash(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
        setSizeFull();
        setupGeneralView();
        setupTopLayout();
        setupNavbarLayout();
        setupBottomLayout();
        setupPostActionsLayout();
        setupWallLayout();
        setupWidgetsLayout();
    }

    public void setupGeneralView() {
        setSizeFull();
        add(generalView);
        generalView.setOrientation(SplitLayout.Orientation.VERTICAL);
        generalView.addToPrimary(topLayout);
        generalView.addToSecondary(bottomLayout);
        generalView.setSizeFull();
        generalView.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        generalView.setPrimaryStyle("minHeight", "170px");
        generalView.setPrimaryStyle("maxHeight", "170px");
    }

    public void setupTopLayout() {
        topLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        topLayout.addToPrimary(new Button("here is toptop"));
        topLayout.setWidthFull();
        topLayout.setHeight("300px");
        topLayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        topLayout.setPrimaryStyle("minHeight", "110px");
        topLayout.setPrimaryStyle("maxHeight", "110px");
    }

    public void setupNavbarLayout() {
        topLayout.addToSecondary(new Button("here is nav"));
    }

    public void setupBottomLayout() {
        bottomLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        bottomLayout.addToSecondary(secondBottomLayout);
        bottomLayout.setSizeFull();
        bottomLayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        bottomLayout.setPrimaryStyle("minWidth", "300px");
        bottomLayout.setPrimaryStyle("maxWidth", "300px");
        bottomLayout.setSecondaryStyle("minWidth", "1200px");
        bottomLayout.setSecondaryStyle("maxWidth", "1200px");
    }

    public void setupPostActionsLayout() {
        TextArea whatDoYouThinkTextArea = new TextArea("Want to share something?");
        whatDoYouThinkTextArea.setWidthFull();
        whatDoYouThinkTextArea.setHeight("150px");
        whatDoYouThinkTextArea.setPreventInvalidInput(true);
        Button createPostButton = new Button("post!");
        createPostButton.addClickListener(buttonClickEvent -> {
            Post post = new Post();
            post.setAuthor(userService.getUserByUsername("dummy"));
            post.setDateTime(LocalDateTime.now());
            post.setContent(whatDoYouThinkTextArea.getValue());
            postService.savePost(post);
        });
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(whatDoYouThinkTextArea, createPostButton);
        bottomLayout.addToPrimary(verticalLayout);
    }

    public void setupWallLayout() {
        secondBottomLayout.addToPrimary(new Button("wall"));
        secondBottomLayout.setSizeFull();
        secondBottomLayout.addThemeVariants(SplitLayoutVariant.LUMO_SMALL);
        secondBottomLayout.setPrimaryStyle("minWidth", "900px");
        secondBottomLayout.setPrimaryStyle("maxWidth", "900px");
        secondBottomLayout.setSecondaryStyle("minWidth", "300px");
        secondBottomLayout.setSecondaryStyle("maxWidth", "300px");

        List<Post> posts = postService.getAllPosts();

        Comparator<Post> compareByDate = Comparator.comparing(Post::getDateTime).reversed();

        posts.sort(compareByDate);

        VerticalLayout verticalLayout = new VerticalLayout();
        secondBottomLayout.addToPrimary(verticalLayout);
        for (Post post : posts) {
            verticalLayout.add(new PostWallForm(post), new Paragraph("================="));
        }
    }

    public void setupWidgetsLayout() {
        secondBottomLayout.addToSecondary(new Button("api"));
    }
}

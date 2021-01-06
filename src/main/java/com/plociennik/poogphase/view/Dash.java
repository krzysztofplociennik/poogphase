package com.plociennik.poogphase.view;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.service.PostService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Route("dash")
@PageTitle("Dashboard")
@Component
@UIScope
public class Dash extends HorizontalLayout {
    private final PostService postService;
    VerticalLayout createNewPostLayout = new VerticalLayout();
    VerticalLayout postsLayout = new VerticalLayout();
    VerticalLayout apiWidgetsLayout = new VerticalLayout();

    SplitLayout generalView = new SplitLayout();
    SplitLayout topLayout = new SplitLayout();
    SplitLayout bottomLayout = new SplitLayout();
    SplitLayout thirdBottomLayout = new SplitLayout();


    @Autowired
    public Dash(PostService postService) {
        this.postService = postService;
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
    }

    public void setupTopLayout() {
        topLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        topLayout.addToPrimary(new Button("here is toptop"));
        topLayout.setSizeFull();
    }

    public void setupNavbarLayout() {
        topLayout.addToSecondary(new Button("here is nav"));
    }

    public void setupBottomLayout() {
        bottomLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        bottomLayout.addToSecondary(thirdBottomLayout);
        bottomLayout.setSizeFull();
    }

    public void setupPostActionsLayout() {
        bottomLayout.addToPrimary(new Button("actions"));
    }

    public void setupWallLayout() {
        thirdBottomLayout.addToPrimary(new Button("wall"));
        thirdBottomLayout.setSizeFull();

        List<Post> unsortedPosts = postService.getAllPosts();

        Comparator<Post> compareByDate = Comparator.comparing(Post::getDateTime);

        unsortedPosts.sort(compareByDate);

        for (Post post : unsortedPosts) {
            thirdBottomLayout.addToPrimary(new PostWallForm(post), new Paragraph("================="));
        }
    }

    public void setupWidgetsLayout() {
        thirdBottomLayout.addToSecondary(new Button("api"));
    }
}

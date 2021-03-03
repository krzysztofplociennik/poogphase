package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.PostWallForm;
import com.plociennik.poogphase.view.logic.SessionManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Route("profile")
@PageTitle("Profile")
public class Profile extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout firstPartOfProfilePageContent;
    private SplitLayout secondPartOfProfilePageContent;
    private ApiClient apiClient;
    private SessionManager sessionManager;

    @Autowired
    public Profile(ApiClient apiClient) {
        this.apiClient = apiClient;
        setSizeFull();
        setupProfilePageContentView();
    }

    public void setupProfilePageContentView() {
        this.generalView = new GeneralView();
        this.firstPartOfProfilePageContent = new SplitLayout();
        this.secondPartOfProfilePageContent = new SplitLayout();
        this.sessionManager = new SessionManager(this.apiClient);

        generalView.setSizeFull();
        generalView.addToSecondary(firstPartOfProfilePageContent);
        firstPartOfProfilePageContent.addToSecondary(secondPartOfProfilePageContent);

        setupInfoView();
        setupUserPostWallView();
        setupPicturesView();

        add(generalView);
    }

    public void setupInfoView() {
        VerticalLayout infoLayout = new VerticalLayout();
        firstPartOfProfilePageContent.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        firstPartOfProfilePageContent.setPrimaryStyle("minWidth", "300px");
        firstPartOfProfilePageContent.setPrimaryStyle("maxWidth", "300px");

        infoLayout.add(new H5("Your account info"));

        firstPartOfProfilePageContent.addToPrimary(infoLayout);
    }

    public void setupUserPostWallView() {
        VerticalLayout userPostsLayout = new VerticalLayout();
        secondPartOfProfilePageContent.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        secondPartOfProfilePageContent.setPrimaryStyle("minWidth", "950px");
        secondPartOfProfilePageContent.setPrimaryStyle("maxWidth", "950px");
        secondPartOfProfilePageContent.addToPrimary(userPostsLayout);

        List<PostDto> reverseSortedUserPosts = apiClient.getPosts().stream()
                .filter(postDto -> postDto.getAuthorId() == sessionManager.getLoggedUser().getId())
                .sorted(Comparator.comparing(PostDto::getDateTime).reversed())
                .collect(Collectors.toList());

        Button closeViewButton = new Button("close", buttonClickEvent -> setupPicturesView());
        for (PostDto post : reverseSortedUserPosts) {
            userPostsLayout.add(new PostWallForm(this.apiClient, post, secondPartOfProfilePageContent, closeViewButton), new Paragraph("================="));
        }
    }

    public void setupPicturesView() {
        secondPartOfProfilePageContent.addToSecondary(new Button("pictures placeholder"));
    }
}

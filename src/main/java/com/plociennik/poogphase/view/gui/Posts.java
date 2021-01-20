package com.plociennik.poogphase.view.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("posts")
@PageTitle("Posts")
public class Posts extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout content;

    public Posts() {
        setSizeFull();
        setupContentView();

        add(generalView);
    }

    public void setupContentView() {
        this.generalView = new GeneralView();
        this.content = new SplitLayout();
        generalView.setSizeFull();
        generalView.addToSecondary(content);

        SplitLayout secondPiece = new SplitLayout();

        content.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        content.setPrimaryStyle("minWidth", "750px");
        content.setPrimaryStyle("maxWidth", "750px");
        content.addToPrimary(new Button("First"));
        content.addToSecondary(secondPiece);

        secondPiece.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        secondPiece.setPrimaryStyle("minWidth", "350px");
        secondPiece.setPrimaryStyle("maxWidth", "350px");
        secondPiece.addToPrimary(new Button("second"));
        secondPiece.addToSecondary(new Button("third"));
    }
}

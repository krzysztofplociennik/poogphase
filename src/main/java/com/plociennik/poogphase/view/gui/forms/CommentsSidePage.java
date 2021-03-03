package com.plociennik.poogphase.view.gui.forms;

import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.CommentListForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentsSidePage extends VerticalLayout {
    private ApiClient apiClient;

    @Autowired
    public CommentsSidePage(ApiClient apiClient, PostDto post, SplitLayout sidePage, Button cancelButton) {
        this.apiClient = apiClient;

        add(cancelButton);
        for (CommentDto comment : post.getComments()) {
            add(new CommentListForm(this.apiClient, comment));
        }
        sidePage.addToSecondary(this);
    }
}
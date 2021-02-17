package com.plociennik.poogphase.view.gui;

import com.plociennik.poogphase.model.dto.ChatMessageDto;
import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.gui.forms.ChatMessageForm;
import com.plociennik.poogphase.view.logic.FriendsManager;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Route("chat")
@PageTitle("Chat")
@UIScope
public class Chat extends HorizontalLayout {
    private GeneralView generalView;
    private SplitLayout chatPageContentLayout;
    private ApiClient apiClient;

    @Autowired
    public Chat(ApiClient apiClient) {
        this.apiClient = apiClient;

        setSizeFull();
        setupChatPageContentView();
        pickUserView();
    }

    public void setupChatPageContentView() {
        this.generalView = new GeneralView();
        this.chatPageContentLayout = new SplitLayout();

        chatPageContentLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);
        chatPageContentLayout.setPrimaryStyle("minWidth", "450px");
        chatPageContentLayout.setPrimaryStyle("maxWidth", "450px");
        generalView.addToSecondary(chatPageContentLayout);
        add(generalView);
    }

    public void pickUserView() {
        FriendsManager friendsManager = new FriendsManager(this.apiClient);
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        UserDto loggedUser = apiClient.getUsers().stream()
                .filter(userDto -> userDto.getUsername().equals("dummy"))
                .findAny().get();

        TextField searchUserTextField = new TextField();
        searchUserTextField.setPlaceholder("type username");
        Icon searchIcon = new Icon(VaadinIcon.ANGLE_RIGHT);
        searchIcon.addClickListener(iconClickEvent -> searchForUser());
        H4 pickUserText = new H4("You can search for someone to send them a message");
        H4 friendPickText = new H4("Or you can pick someone from your friend list");

        verticalLayout.add(pickUserText, horizontalLayout, friendPickText);

        for (UserDto friend : friendsManager.searchFriends(loggedUser)) {
            verticalLayout.add(new Button(friend.getUsername(), buttonClickEvent -> chatWindowView(friend)));
        }

        chatPageContentLayout.addToPrimary(verticalLayout);
    }

    public void chatWindowView(UserDto selectedUser) {
        VerticalLayout chatWindowLayout = new VerticalLayout();
        VerticalLayout messagesListLayout = new VerticalLayout();
        HorizontalLayout sendMessageLayout = new HorizontalLayout();

        UserDto loggedUser = apiClient.getUsers().stream()
                .filter(userDto -> userDto.getUsername().equals("dummy"))
                .findAny()
                .get();

        List<ChatMessageDto> chatLog = loggedUser.getMessages().stream()
                .filter(chatMessageDto -> chatMessageDto.getRecipient().equals(selectedUser.getUsername()))
                .collect(Collectors.toList());
        chatLog.addAll(selectedUser.getMessages().stream()
                .filter(chatMessageDto -> chatMessageDto.getRecipient().equals(loggedUser.getUsername()))
                .collect(Collectors.toList()));

        chatLog = chatLog.stream().sorted(Comparator.comparing(ChatMessageDto::getDateTime))
                .collect(Collectors.toList());

        if (chatLog.size() == 0) {
            messagesListLayout.add(new H4("Well, there's actually nothing here..."));
        }

        for (ChatMessageDto message : chatLog) {
            messagesListLayout.add(new ChatMessageForm(this.apiClient, message));
        }

        TextField writeMessageTextField = new TextField();
        writeMessageTextField.setWidth("900px");
        Button sendMessageButton = new Button("send", buttonClickEvent -> {
            try {
                sendMessage(loggedUser, selectedUser, writeMessageTextField.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
            chatWindowView(selectedUser);
        });
        sendMessageButton.addClickShortcut(Key.ENTER);

        sendMessageLayout.add(writeMessageTextField, sendMessageButton);
        sendMessageLayout.setWidthFull();
        sendMessageLayout.setHeight("100px");

        messagesListLayout.setWidthFull();
        messagesListLayout.setHeight("700px");
        chatWindowLayout.add(messagesListLayout, sendMessageLayout);
        chatPageContentLayout.addToSecondary(chatWindowLayout);
    }

    public void searchForUser() {

    }

    public void searchResultsView() {

    }

    public void sendMessage(UserDto author, UserDto recipient, String message) throws IOException {
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setDateTime(LocalDateTime.now());
        chatMessageDto.setAuthorId(author.getId());
        chatMessageDto.setRecipient(recipient.getUsername());
        chatMessageDto.setContent(message);
        apiClient.createMessage(chatMessageDto);
    }
}

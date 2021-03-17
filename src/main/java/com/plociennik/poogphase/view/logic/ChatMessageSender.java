package com.plociennik.poogphase.view.logic;

import com.plociennik.poogphase.model.dto.ChatMessageDto;
import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChatMessageSender {
    private ApiClient apiClient;

    @Autowired
    public ChatMessageSender(ApiClient apiClient) {
        this.apiClient = apiClient;
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

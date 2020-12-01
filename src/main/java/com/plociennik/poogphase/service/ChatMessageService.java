package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    public ChatMessage saveMessage(ChatMessage message) {
        message.getChatLog().getLog().add(message);
        message.getChatLog().setLog(message.getChatLog().getLog());
        return chatMessageRepository.save(message);
    }

    public Optional<ChatMessage> getMessage(final long id) {
        return chatMessageRepository.findById(id);
    }

    public void removeMessage(long id) {
        chatMessageRepository.deleteById(id);
    }

    public ChatMessage findByContent(String content) {
        return chatMessageRepository.findByContent(content);
    }

}

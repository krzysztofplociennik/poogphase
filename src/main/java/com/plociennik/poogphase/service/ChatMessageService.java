package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private UserService userService;

    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    public ChatMessage saveMessage(ChatMessage message) {
        Optional<ChatMessage> optionalChatMessage = message.getAuthor().getMessages().stream()
                .filter(chatMessage -> chatMessage.getId() == message.getId())
                .findAny();
        if (optionalChatMessage.isEmpty()) {
            message.getAuthor().getMessages().add(message);
            message.getAuthor().setMessages(message.getAuthor().getMessages());
            return chatMessageRepository.save(message);
        } else {
            optionalChatMessage.get().setRecipient(message.getRecipient());
            optionalChatMessage.get().setAuthor(message.getAuthor());
            optionalChatMessage.get().setContent(message.getContent());
            optionalChatMessage.get().setDateTime(message.getDateTime());
            return chatMessageRepository.save(optionalChatMessage.get());
        }
    }

    public Optional<ChatMessage> getMessage(final long id) {
        return chatMessageRepository.findById(id);
    }

    public void removeMessage(long id) {
        ChatMessage chatMessage = chatMessageRepository.findById(id).get();
        User author = chatMessage.getAuthor();
        author.getMessages().remove(chatMessage);
        userService.saveUser(author);
    }

    public ChatMessage getByContent(String content) {
        return chatMessageRepository.findByContent(content);
    }

}

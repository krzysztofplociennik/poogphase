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

    public ChatMessage saveMessage(final ChatMessage message) {
        User author = userService.getUserByUsername(message.getAuthor().getUsername());
        author.getMessages().add(message);
        userService.saveUser(author);
        return chatMessageRepository.save(message);
    }

    public Optional<ChatMessage> getMessage(final long id) {
        return chatMessageRepository.findById(id);
    }

    public void removeMessage(final long id) {
        ChatMessage chatMessage = chatMessageRepository.findById(id).get();
        User author = chatMessage.getAuthor();
        author.getMessages().remove(chatMessage);
        userService.saveUser(author);
        chatMessageRepository.deleteById(id);
    }

    public ChatMessage getByContent(final String content) {
        return chatMessageRepository.findByContent(content);
    }

}

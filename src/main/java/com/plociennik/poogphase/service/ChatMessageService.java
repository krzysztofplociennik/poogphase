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
        User recipient = userService.getUserByUsername(message.getRecipient());
        author.getMessages().add(message);
        recipient.getMessages().add(message);
//        userService.getUserByUsername(message.getRecipient()).getMessages().add(message);
        userService.saveUser(author);
        userService.saveUser(recipient);
//        Optional<ChatMessage> optionalChatMessage = message.getAuthor().getMessages().str
//        eam()
//                .filter(chatMessage -> chatMessage.getId() == message.getId())
//                .findAny();
//        if (optionalChatMessage.isEmpty()) {
////            message.getAuthor().getMessages().add(message);
////            message.getAuthor().setMessages(message.getAuthor().getMessages());
//            return chatMessageRepository.save(message);
//        } else {
//            optionalChatMessage.get().setRecipient(message.getRecipient());
//            optionalChatMessage.get().setAuthor(message.getAuthor());
//            optionalChatMessage.get().setContent(message.getContent());
//            optionalChatMessage.get().setDateTime(message.getDateTime());
//            return chatMessageRepository.save(optionalChatMessage.get());
//        }
        return chatMessageRepository.save(message);
    }

    public Optional<ChatMessage> getMessage(final long id) {
        return chatMessageRepository.findById(id);
    }

    public void removeMessage(final long id) {
        ChatMessage chatMessage = chatMessageRepository.findById(id).get();
        User recipient = userService.getUserByUsername(chatMessage.getRecipient());
        recipient.getMessages().remove(chatMessage);
        userService.saveUser(recipient);
//        User author = chatMessage.getAuthor();
//        author.getMessages().remove(chatMessage);
//        userService.saveUser(author);
    }

    public ChatMessage getByContent(final String content) {
        return chatMessageRepository.findByContent(content);
    }

}

package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLogService {
    @Autowired
    private ChatLogRepository repository;

    public List<ChatLog> getAllChatLogs() {
        return repository.findAll();
    }

    public ChatLog saveChatLog(ChatLog chatLog) {
        return repository.save(chatLog);
    }

    public Optional<ChatLog> getChatLog(final long id) {
        return repository.findById(id);
    }

    public void removeChatLog(long id) {
        repository.deleteById(id);
    }

    public ChatLog findBySignature(String signature) {
        return repository.findBySignature(signature);
    }
}

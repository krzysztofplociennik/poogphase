package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Override
    List<ChatMessage> findAll();

    ChatMessage findByContent(String content);
}

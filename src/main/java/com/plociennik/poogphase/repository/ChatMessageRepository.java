package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Override
    List<ChatMessage> findAll();

    ChatMessage findByContent(String content);
}

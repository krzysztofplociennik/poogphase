package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    @Override
    List<ChatLog> findAll();
}

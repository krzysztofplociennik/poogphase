package com.plociennik.poogphase.repository.other;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VariousTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void wipeAllRecords() {
        for (JpaRepository instance : Arrays.asList(userRepository, chatLogRepository, chatMessageRepository, postRepository, commentRepository)) {
//            instance.deleteAll();
            System.out.println("Records: " + instance.findAll().size());
        }
    }
}

package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.Post;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatLogRepositoryTestSuite {
    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Before
    public void initSomeData() {
        if (chatLogRepository.count() > 0) {
            chatLogRepository.deleteAll();
        }
        ChatLog chatLog = new ChatLog(1L,
                "mark&paula",
                new ArrayList<>());
        ChatLog chatLog2 = new ChatLog(1L,
                "mark&peter",
                new ArrayList<>());
        ChatLog chatLog3 = new ChatLog(1L,
                "peter&paula",
                new ArrayList<>());
        ChatLog chatLog4 = new ChatLog();
        chatLog4.setSignature("mark&victoria");

        chatLogRepository.saveAll(Arrays.asList(chatLog, chatLog2, chatLog3, chatLog4));
    }

    @After
    public void cleanUpData() {
        chatLogRepository.deleteById(chatLogRepository.findBySignature("mark&paula").getId());
        chatLogRepository.deleteById(chatLogRepository.findBySignature("mark&peter").getId());
        chatLogRepository.deleteById(chatLogRepository.findBySignature("peter&paula").getId());
        chatLogRepository.deleteById(chatLogRepository.findBySignature("mark&victoria").getId());
        System.out.println("There are " + chatLogRepository.findAll().size() + " records now.");
    }

    @Test
    public void checkRepositorySize() {
    }

    @Test
    public void saveChatLog() {
        //Given
        long sizeBeforeSaving = chatLogRepository.count();
        ChatLog dummyLog = new ChatLog();
        dummyLog.setSignature("dummy");
        //When
        chatLogRepository.save(dummyLog);
        long dummyId = chatLogRepository.findBySignature("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, chatLogRepository.count());
        //Clean up
        chatLogRepository.deleteById(dummyId);
        Assert.assertEquals(sizeBeforeSaving, chatLogRepository.count());
    }

    @Test
    public void getAlChatLogs() {
        //Given
        long sizeBeforeSaving = chatLogRepository.count();
        ChatLog log1 = new ChatLog();
        log1.setSignature("log1");
        ChatLog log2 = new ChatLog();
        log2.setSignature("log2");
        //When
        chatLogRepository.saveAll(Arrays.asList(log1, log2));
        long dummyId1 = chatLogRepository.findBySignature("log1").getId();
        long dummyId2 = chatLogRepository.findBySignature("log2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, chatLogRepository.count());
        //Clean up
        chatLogRepository.deleteById(dummyId1);
        chatLogRepository.deleteById(dummyId2);
    }

    @Test
    public void getChatLog() {
        //Given

        //When
        ChatLog searchedLog = chatLogRepository.findBySignature("mark&paula");
        //Then
        Assert.assertEquals("mark&paula", searchedLog.getSignature());
        //Clean up
    }

    @Test
    public void editChatLog() {
        //Given
        long sizeBeforeEditing = chatLogRepository.count();
        //When
        ChatLog searchedLog = chatLogRepository.findBySignature("peter&paula");
        ChatMessage chatMessage = new ChatMessage();
        chatMessageRepository.save(chatMessage);

        System.out.println(searchedLog.getLog().add(new ChatMessage()));
        chatLogRepository.save(searchedLog);
        System.out.println(chatLogRepository.findBySignature("peter&paula").getLog().size());
        //Then
        Assert.assertEquals(sizeBeforeEditing, chatLogRepository.count());
//        Assert.assertEquals(1, searchedLog.getLog().size());
        //Clean up
    }

    @Test
    public void deleteChatLog() {
        //Given
        long sizeBeforeDeleting = chatLogRepository.count();
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("dummy");
        chatLogRepository.save(chatLog);
        //When
        long dummyId = chatLogRepository.findBySignature("dummy").getId();
        chatLogRepository.deleteById(dummyId);
        //Then
        Assert.assertEquals(sizeBeforeDeleting, chatLogRepository.count());
    }

    @Test
    public void testIfMessagesAreNotNullUponCreatingNewChatLog() {
        //Given
        //When
        ChatLog searchedLog = chatLogRepository.findBySignature("mark&victoria");
        //Then
        Assert.assertNotEquals(null, searchedLog.getLog());
        //Clean up
    }

    @Test
    public void showNumberOfRecords() {

    }
}

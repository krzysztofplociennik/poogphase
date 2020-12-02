package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.ChatMessage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatLogRepositoryTestSuite {
    @Autowired
    private ChatLogRepository chatLogRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    private long initialChatLogRepositorySize;
    private long initialChatMessageRepositorySize;

    @Before
    public void init() {
        initialChatMessageRepositorySize = chatMessageRepository.count();
        initialChatLogRepositorySize = chatLogRepository.count();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialChatMessageRepositorySize, chatMessageRepository.count());
        Assert.assertEquals(initialChatLogRepositorySize, chatLogRepository.count());
        System.out.println("There are " + chatLogRepository.count() + " records now.");
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
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("mark&paula");
        chatLogRepository.save(chatLog);
        //When
        ChatLog searchedLog = chatLogRepository.findBySignature("mark&paula");
        //Then
        Assert.assertEquals("mark&paula", searchedLog.getSignature());
        //Clean up
        chatLogRepository.deleteById(searchedLog.getId());
    }

    @Test
    public void editChatLog() {
        //Given
        //When
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("peter&paula");
        chatLogRepository.save(chatLog);

        long sizeBeforeEditing = chatLogRepository.count();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("editContentMessage");
        chatMessage.setChatLog(chatLog);
        chatMessageRepository.save(chatMessage);

        chatLog.getLog().add(chatMessage);
        chatLogRepository.save(chatLog);

        long searchedChatLogId = chatLogRepository.findBySignature("peter&paula").getId();
        long searchedChatMessageId = chatMessageRepository.findByContent("editContentMessage").getId();
        //Then
        Assert.assertEquals(sizeBeforeEditing, chatLogRepository.count());
        Assert.assertEquals(1, chatLogRepository.findBySignature("peter&paula").getLog().size());
        //Clean up
        chatMessageRepository.deleteById(searchedChatMessageId);
        chatLogRepository.deleteById(searchedChatLogId);
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
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("mark&victoria");
        chatLogRepository.save(chatLog);

        long searchedLogId = chatLogRepository.findBySignature("mark&victoria").getId();

        //Then
        Assert.assertNotEquals(null, chatLogRepository.findBySignature("mark&victoria").getLog());
        //Clean up
        chatLogRepository.deleteById(searchedLogId);
    }

    @Test
    public void showNumberOfRecords() {

    }
}

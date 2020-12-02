package com.plociennik.poogphase.service;

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
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatLogServiceTestSuite {
    @Autowired
    private ChatLogService chatLogService;
    @Autowired
    private ChatMessageService chatMessageService;
    private long initialChatLogRepositorySize;


    @Before
    public void init() {
        initialChatLogRepositorySize = chatLogService.getAllChatLogs().size();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialChatLogRepositorySize, chatLogService.getAllChatLogs().size());
    }

    @Test
    public void saveChatLog() {
        //Given
        long sizeBeforeSaving = chatLogService.getAllChatLogs().size();
        ChatLog dummyLog = new ChatLog();
        dummyLog.setSignature("dummy");
        //When
        chatLogService.saveChatLog(dummyLog);
        long dummyId = chatLogService.findBySignature("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, chatLogService.getAllChatLogs().size());
        //Clean up
        chatLogService.removeChatLog(dummyId);
    }

    @Test
    public void getAllChatLogs() {
        //Given
        long sizeBeforeSaving = chatLogService.getAllChatLogs().size();
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("log");
        ChatLog chatLog1 = new ChatLog();
        chatLog1.setSignature("log1");
        //When
        chatLogService.saveChatLog(chatLog);
        chatLogService.saveChatLog(chatLog1);
        long dummyId1 = chatLogService.findBySignature("log").getId();
        long dummyId2 = chatLogService.findBySignature("log1").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, chatLogService.getAllChatLogs().size());
        //Clean up
        chatLogService.removeChatLog(dummyId1);
        chatLogService.removeChatLog(dummyId2);
    }

    @Test
    public void getChatLog() {
        //Given
        //When
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("atomix&paul");
        chatLogService.saveChatLog(chatLog);
        long searchedLogId = chatLogService.findBySignature("atomix&paul").getId();
        //Then
        Assert.assertTrue(chatLogService.getChatLog(searchedLogId).isPresent());
        Assert.assertEquals("atomix&paul", chatLogService.getChatLog(searchedLogId).get().getSignature());
        //Clean up
        chatLogService.removeChatLog(searchedLogId);
    }

    @Test
    public void editChatLog() {
        //Given
        //When
        chatLogService.saveChatLog(new ChatLog(1L, "atomix&victoria", new ArrayList<>()));
        long sizeBeforeEditing = chatLogService.getAllChatLogs().size();
        ChatLog searchedLog = chatLogService.findBySignature("atomix&victoria");
        long logId = searchedLog.getId();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setChatLog(searchedLog);
        chatMessageService.saveMessage(chatMessage);

        chatLogService.saveChatLog(searchedLog);

        //Then
        Assert.assertEquals(sizeBeforeEditing, chatLogService.getAllChatLogs().size());
        Assert.assertEquals(1, chatLogService.getChatLog(logId).get().getLog().size());
        //Clean up
        chatLogService.removeChatLog(logId);
    }

    @Test
    public void deleteChatLog() {
        //Given
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("dummy");
        chatLogService.saveChatLog(chatLog);
        //When
        long dummyId = chatLogService.findBySignature("dummy").getId();
        chatLogService.removeChatLog(dummyId);
        //Then
    }

    @Test
    public void testIfMessagesAreNotNullUponCreatingNewChatLog() {
        //Given
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("paul&marqez");
        chatLogService.saveChatLog(chatLog);
        //When
        long searchedLogId = chatLogService.findBySignature("paul&marqez").getId();
        //Then
        Assert.assertNotEquals(null, chatLogService.getChatLog(searchedLogId).get().getLog());
        //Clean up
        chatLogService.removeChatLog(searchedLogId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatLogService.getAllChatLogs().size());
    }

}

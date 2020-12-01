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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatMessageServiceTestSuite {
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatLogService chatLogService;
    private long initialChatMessageRepositorySize;
    private long initialChatLogRepositorySize;

    @Before
    public void initSomeData() {
        initialChatMessageRepositorySize = chatMessageService.getAllMessages().size();
        initialChatLogRepositorySize = chatLogService.getAllChatLogs().size();
    }

    @After
    public void cleanUpData() {
        Assert.assertEquals(initialChatMessageRepositorySize, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialChatLogRepositorySize, chatLogService.getAllChatLogs().size());
    }

    @Test
    public void saveChatMessage() {
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("saveMessageDummy");
        chatLogService.saveChatLog(chatLog);

        chatMessageService.saveMessage(new ChatMessage(1L, null, "", "saveMessageContent", null, chatLog));

        long searchedMessageId = chatMessageService.findByContent("saveMessageContent").getId();
        long searchedChatLogId = chatLogService.findBySignature("saveMessageDummy").getId();

        Assert.assertTrue(chatMessageService.getMessage(searchedMessageId).isPresent());
        //Clean up
        chatMessageService.removeMessage(searchedMessageId);
        chatLogService.removeChatLog(searchedChatLogId);
    }

    @Test
    public void getAllChatMessages() {
        long initialRepositorySize = chatMessageService.getAllMessages().size();
        ChatLog chatLog = new ChatLog();
        ChatLog chatLog2 = new ChatLog();
        chatLog.setSignature("dummy");
        chatLog2.setSignature("dummy2");
        chatLogService.saveChatLog(chatLog);
        chatLogService.saveChatLog(chatLog2);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(20, 45));
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.of(2020, 12, 30), LocalTime.of(21, 45));
        ChatMessage chatMessage = new ChatMessage(111L, null, "", "content", dateTime, chatLog);
        ChatMessage chatMessage2 = new ChatMessage(222L, null, "", "content2", dateTime2, chatLog2);
        chatMessageService.saveMessage(chatMessage);
        chatMessageService.saveMessage(chatMessage2);
        long searchedMessageId = chatMessageService.findByContent("content").getId();
        long searchedMessageId2 = chatMessageService.findByContent("content2").getId();
        long searchedChatLogId = chatLogService.findBySignature("dummy").getId();
        long searchedChatLogId2 = chatLogService.findBySignature("dummy2").getId();

        Assert.assertEquals(initialRepositorySize + 2, chatMessageService.getAllMessages().size());
        //Clean up
        chatMessageService.removeMessage(searchedMessageId);
        chatMessageService.removeMessage(searchedMessageId2);
        chatLogService.removeChatLog(searchedChatLogId);
        chatLogService.removeChatLog(searchedChatLogId2);
    }

    @Test
    public void getChatMessage() {
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("dummy");
        chatLogService.saveChatLog(chatLog);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(20, 45));
        chatMessageService.saveMessage(new ChatMessage(1L, null, "", "content", dateTime, chatLog));

        long searchedMessageId = chatMessageService.findByContent("content").getId();
        long searchedChatLogId = chatLogService.findBySignature("dummy").getId();

        ChatMessage searchedMessage = chatMessageService.getMessage(searchedMessageId).get();

        Assert.assertEquals("content", searchedMessage.getContent());
        //Clean up
        chatMessageService.removeMessage(searchedMessageId);
        chatLogService.removeChatLog(searchedChatLogId);
    }

    @Test
    public void editChatMessage() {
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("dummy");
        chatLogService.saveChatLog(chatLog);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(20, 45));
        ChatMessage chatMessage = new ChatMessage(1L, null, "", "testContent", dateTime, chatLog);
        chatMessageService.saveMessage(chatMessage);
        ChatMessage searchedMessage = chatMessageService.findByContent("testContent");

        long searchedChatLogId = chatLogService.findBySignature("dummy").getId();

        searchedMessage.setRecipient("mark");
        chatMessageService.saveMessage(searchedMessage);

        Assert.assertEquals("mark", chatMessageService.getMessage(searchedMessage.getId()).get().getRecipient());
        //Clean up
        chatMessageService.removeMessage(searchedMessage.getId());
        chatLogService.removeChatLog(searchedChatLogId);
    }

    @Test
    public void deleteChatMessage() {
        ChatLog chatLog = new ChatLog();
        chatLog.setSignature("dummy");
        chatLogService.saveChatLog(chatLog);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(20, 45));
        chatMessageService.saveMessage(new ChatMessage(1L, null, "", "content", dateTime, chatLog));

        long searchedMessageId = chatMessageService.findByContent("content").getId();
        long searchedChatLogId = chatLogService.findBySignature("dummy").getId();

        chatMessageService.removeMessage(chatMessageService.getMessage(searchedMessageId).get().getId());

        //Clean up
        chatLogService.removeChatLog(searchedChatLogId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageService.getAllMessages().size());
    }
}

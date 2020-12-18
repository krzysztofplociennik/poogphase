package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;
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
    private UserService userService;
    private long initialChatMessageRepositorySize;

    @Before
    public void init() {
        initialChatMessageRepositorySize = chatMessageService.getAllMessages().size();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialChatMessageRepositorySize, chatMessageService.getAllMessages().size());
    }

    @Test
    public void saveChatMessage() {
        User user = userService.getUserByUsername("dummy");
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", "saveMessageContent", null));

        long searchedMessageId = chatMessageService.getByContent("saveMessageContent").getId();

        Assert.assertTrue(chatMessageService.getMessage(searchedMessageId).isPresent());
        //Clean up
        chatMessageService.removeMessage2(searchedMessageId);
    }

    @Test
    public void getAllChatMessages() {
        User user = userService.getUserByUsername("dummy");
        long initialRepositorySize = chatMessageService.getAllMessages().size();
        ChatMessage chatMessage = new ChatMessage(111L, user, "", "content", null);
        ChatMessage chatMessage2 = new ChatMessage(222L, user, "", "content2", null);
        chatMessageService.saveMessage(chatMessage);
        chatMessageService.saveMessage(chatMessage2);
        long searchedMessageId = chatMessageService.getByContent("content").getId();
        long searchedMessageId2 = chatMessageService.getByContent("content2").getId();

        Assert.assertEquals(initialRepositorySize + 2, chatMessageService.getAllMessages().size());
        //Clean up
        chatMessageService.removeMessage2(searchedMessageId);
        chatMessageService.removeMessage2(searchedMessageId2);
    }

    @Test
    public void getChatMessage() {
        User user = userService.getUserByUsername("dummy");
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", "content", null));

        long searchedMessageId = chatMessageService.getByContent("content").getId();

        ChatMessage searchedMessage = chatMessageService.getMessage(searchedMessageId).get();

        Assert.assertEquals("content", searchedMessage.getContent());
        //Clean up
        chatMessageService.removeMessage2(searchedMessageId);
    }

    @Test
    public void editChatMessage() {
        User user = userService.getUserByUsername("dummy");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(20, 45));
        ChatMessage chatMessage = new ChatMessage(1L, user, "", "testContent", dateTime);
        chatMessageService.saveMessage(chatMessage);
        ChatMessage searchedMessage = chatMessageService.getByContent("testContent");

        searchedMessage.setRecipient("mark");
        chatMessageService.saveMessage(searchedMessage);

        Assert.assertEquals("mark", chatMessageService.getMessage(searchedMessage.getId()).get().getRecipient());
        //Clean up
        chatMessageService.removeMessage2(searchedMessage.getId());
    }

    @Test
    public void deleteChatMessage() {
        User user = userService.getUserByUsername("dummy");
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", "content", null));

        long searchedMessageId = chatMessageService.getByContent("content").getId();

        chatMessageService.removeMessage2(chatMessageService.getMessage(searchedMessageId).get().getId());

        //Clean up
    }

    @Test
    public void saveAndDelete2Message() {
        User user = userService.getUserByUsername("dummy");
        long initialSizeOfMessagesInRep = chatMessageService.getAllMessages().size();
        long initialSizeOfMessagesInUser = user.getMessages().size();
        chatMessageService.saveMessage2(new ChatMessage(1L, user, "", "content", null));

        Assert.assertEquals(initialSizeOfMessagesInRep + 1, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());

        long searchedMessageId = chatMessageService.getByContent("content").getId();

        chatMessageService.removeMessage2(searchedMessageId);
        Assert.assertEquals(initialSizeOfMessagesInRep, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser, userService.getUserByUsername("dummy").getMessages().size());
    }

    @Test
    public void editMessage2() {
        User user = userService.getUserByUsername("dummy");
        long initialSizeOfMessagesInRep = chatMessageService.getAllMessages().size();
        long initialSizeOfMessagesInUser = user.getMessages().size();
        chatMessageService.saveMessage2(new ChatMessage(1L, user, "", "content", null));

        Assert.assertEquals(initialSizeOfMessagesInRep + 1, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals("", chatMessageService.getByContent("content").getRecipient());

        long searchedMessageId = chatMessageService.getByContent("content").getId();
        ChatMessage searchedMessage = chatMessageService.getByContent("content");
        searchedMessage.setRecipient("atomix");
        chatMessageService.saveMessage2(searchedMessage);

        Assert.assertEquals(initialSizeOfMessagesInRep + 1, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals("atomix", chatMessageService.getByContent("content").getRecipient());

        chatMessageService.removeMessage2(searchedMessageId);
        Assert.assertEquals(initialSizeOfMessagesInRep, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser, userService.getUserByUsername("dummy").getMessages().size());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageService.getAllMessages().size());
    }
}

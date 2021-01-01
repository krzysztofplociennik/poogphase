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
    public void saveAndDeleteMessage() {
        User user = userService.getUserByUsername("dummy");
        User user2 = userService.getUserByUsername("silly");
        long initialSizeOfMessagesInUser = user.getMessages().size();
        long initialSizeOfMessagesInUser2 = user2.getMessages().size();
        ChatMessage message = new ChatMessage();
        message.setAuthor(user);
        message.setRecipient(user2.getUsername());
        message.setContent("content");
        chatMessageService.saveMessage(message);


        Assert.assertEquals(initialChatMessageRepositorySize + 3, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser2 + 1, userService.getUserByUsername("silly").getMessages().size());

        long searchedMessageId = chatMessageService.getByContent("content").getId();

        chatMessageService.removeMessage(searchedMessageId);
        Assert.assertEquals(initialChatMessageRepositorySize, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser2, userService.getUserByUsername("silly").getMessages().size());
    }

    @Test
    public void getChatMessage() {
        User user = userService.getUserByUsername("dummy");
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", null, "content"));

        long searchedMessageId = chatMessageService.getByContent("content").getId();

        ChatMessage searchedMessage = chatMessageService.getMessage(searchedMessageId).get();

        Assert.assertEquals("content", searchedMessage.getContent());
        //Clean up
        chatMessageService.removeMessage(searchedMessageId);
    }

    @Test
    public void getAllChatMessages() {
        User user = userService.getUserByUsername("dummy");
        long initialRepositorySize = chatMessageService.getAllMessages().size();
        ChatMessage chatMessage = new ChatMessage(111L, user, "", null, "content");
        ChatMessage chatMessage2 = new ChatMessage(222L, user, "", null, "content2");
        chatMessageService.saveMessage(chatMessage);
        chatMessageService.saveMessage(chatMessage2);
        long searchedMessageId = chatMessageService.getByContent("content").getId();
        long searchedMessageId2 = chatMessageService.getByContent("content2").getId();

        Assert.assertEquals(initialRepositorySize + 2, chatMessageService.getAllMessages().size());
        //Clean up
        chatMessageService.removeMessage(searchedMessageId);
        chatMessageService.removeMessage(searchedMessageId2);
    }

    @Test
    public void editMessage() {
        User user = userService.getUserByUsername("dummy");
        long initialSizeOfMessagesInRep = chatMessageService.getAllMessages().size();
        long initialSizeOfMessagesInUser = user.getMessages().size();
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", null, "content"));

        Assert.assertEquals(initialSizeOfMessagesInRep + 1, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals("", chatMessageService.getByContent("content").getRecipient());

        long searchedMessageId = chatMessageService.getByContent("content").getId();
        ChatMessage searchedMessage = chatMessageService.getByContent("content");
        searchedMessage.setRecipient("atomix");
        chatMessageService.saveMessage(searchedMessage);

        Assert.assertEquals(initialSizeOfMessagesInRep + 1, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser + 1, userService.getUserByUsername("dummy").getMessages().size());
        Assert.assertEquals("atomix", chatMessageService.getByContent("content").getRecipient());

        chatMessageService.removeMessage(searchedMessageId);
        Assert.assertEquals(initialSizeOfMessagesInRep, chatMessageService.getAllMessages().size());
        Assert.assertEquals(initialSizeOfMessagesInUser, userService.getUserByUsername("dummy").getMessages().size());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageService.getAllMessages().size());
    }

    @Test
    public void saveData() {
        User user = userService.getUserByUsername("dummy");
        chatMessageService.saveMessage(new ChatMessage(1L, user, "", null, "content"));


    }
}

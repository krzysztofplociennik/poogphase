package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.ChatMessage;
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
public class ChatMessageRepositoryTestSuite {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    private long initialChatMessageRepositorySize;

    @Before
    public void init() {
        initialChatMessageRepositorySize = chatMessageRepository.count();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialChatMessageRepositorySize, chatMessageRepository.count());
    }

    @Test
    public void saveMessage() {
        chatMessageRepository.save(new ChatMessage(1L, null, "", "saveMessageContent", null));

        long searchedMessageId = chatMessageRepository.findByContent("saveMessageContent").getId();

        Assert.assertTrue(chatMessageRepository.findById(searchedMessageId).isPresent());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void getAllMessages() {
        chatMessageRepository.save(new ChatMessage(1L, null, "", "getAllContent", null));
        chatMessageRepository.save(new ChatMessage(1L, null, "", "getAllContent2", null));

        long searchedMessageId = chatMessageRepository.findByContent("getAllContent").getId();
        long searchedMessageId2 = chatMessageRepository.findByContent("getAllContent2").getId();

        Assert.assertEquals(initialChatMessageRepositorySize + 2, chatMessageRepository.count());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
        chatMessageRepository.deleteById(searchedMessageId2);
    }

    @Test
    public void getMessage() {
        chatMessageRepository.save(new ChatMessage(1L, null, "", "getMessageContent", null));

        long searchedMessageId = chatMessageRepository.findByContent("getMessageContent").getId();

        Assert.assertTrue(chatMessageRepository.findById(searchedMessageId).isPresent());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void editMessage() {
        chatMessageRepository.save(new ChatMessage(1L, null, "", "editMessageContent", null));
        long sizeOfRepAfterSaving = chatMessageRepository.count();
        ChatMessage chatMessage = chatMessageRepository.findByContent("editMessageContent");

        Assert.assertEquals("", chatMessage.getRecipient());

        chatMessage.setRecipient("marqez");
        chatMessageRepository.save(chatMessage);

        Assert.assertEquals(sizeOfRepAfterSaving, chatMessageRepository.count());
        Assert.assertEquals("marqez",chatMessageRepository.findByContent("editMessageContent").getRecipient());
        //Clean up
        chatMessageRepository.deleteById(chatMessage.getId());
    }

    @Test
    public void deleteMessage() {
        chatMessageRepository.save(new ChatMessage(1L, null, "", "deleteMessageContent", null));

        long searchedMessageId = chatMessageRepository.findByContent("deleteMessageContent").getId();

        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageRepository.findAll().size());
    }
}   // <(")
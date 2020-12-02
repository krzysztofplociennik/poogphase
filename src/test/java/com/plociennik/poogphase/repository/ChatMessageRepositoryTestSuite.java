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
import java.util.Arrays;

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
        //Given
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("saveMessageContent");
        chatMessageRepository.save(chatMessage);
        //When
        long searchedMessageId = chatMessageRepository.findByContent("saveMessageContent").getId();
        //Then
        Assert.assertTrue(chatMessageRepository.findById(searchedMessageId).isPresent());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void getAllMessages() {
        //Given
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setContent("getAllContent");
        ChatMessage chatMessage2 = new ChatMessage();
        chatMessage2.setContent("getAllContent2");
        chatMessageRepository.saveAll(Arrays.asList(chatMessage1, chatMessage2));
        //When
        long searchedMessageId = chatMessageRepository.findByContent("getAllContent").getId();
        long searchedMessageId2 = chatMessageRepository.findByContent("getAllContent2").getId();
        //Then
        Assert.assertEquals(initialChatMessageRepositorySize + 2, chatMessageRepository.count());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
        chatMessageRepository.deleteById(searchedMessageId2);
    }

    @Test
    public void getMessage() {
        //Given
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("getMessageContent");
        chatMessageRepository.save(chatMessage);
        //When
        long searchedMessageId = chatMessageRepository.findByContent("getMessageContent").getId();
        //Then
        Assert.assertTrue(chatMessageRepository.findById(searchedMessageId).isPresent());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void editMessage() {
        //Given
        //When
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("editMessageContent");
        chatMessageRepository.save(chatMessage);

        chatMessage.setRecipient("marqez");
        chatMessageRepository.save(chatMessage);

        long searchedMessageId = chatMessageRepository.findByContent("editMessageContent").getId();
        //Then
        Assert.assertEquals("marqez", chatMessageRepository.findById(searchedMessageId).get().getRecipient());
        //Clean up
        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void deleteMessage() {
        //Given
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("deleteMessageContent");
        chatMessageRepository.save(chatMessage);
        //When
        long searchedMessageId = chatMessageRepository.findByContent("deleteMessageContent").getId();

        chatMessageRepository.deleteById(searchedMessageId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageRepository.findAll().size());
    }
}

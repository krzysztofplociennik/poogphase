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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatMessageRepositoryTestSuite {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Before
    public void initSomeData() {
        if (chatMessageRepository.findAll().size() > 0) {
            chatMessageRepository.deleteAll();
        }
        ChatMessage msg1 = new ChatMessage(1L, null, "marq", "Heyyy, what's up",
                LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(20, 25)), null);
        ChatMessage msg2 = new ChatMessage(1L, null, "paul", "good good",
                LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(21, 25)), null);
        ChatMessage msg3 = new ChatMessage(1L, null, "atomix", "hahhaha",
                LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(12, 25)), null);
        ChatMessage msg4 = new ChatMessage();
        msg4.setContent("LOL"); msg4.setDateTime(LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(14, 25)));
        chatMessageRepository.saveAll(Arrays.asList(msg1, msg2, msg3, msg4));
    }

    @After
    public void cleanUpData() {
        String cont1 = "Heyyy, what's up", cont2 = "good good", cont3 = "hahhaha", cont4 = "LOL";
        LocalDateTime time1 = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(20, 25));
        LocalDateTime time2 = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(21, 25));
        LocalDateTime time3 = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(12, 25));
        LocalDateTime time4 = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(14, 25));
        chatMessageRepository.deleteById(chatMessageRepository.findByContent(cont1).getId());
        chatMessageRepository.deleteById(chatMessageRepository.findByContent(cont2).getId());
        chatMessageRepository.deleteById(chatMessageRepository.findByContent(cont3).getId());
        chatMessageRepository.deleteById(chatMessageRepository.findByContent(cont4).getId());
        System.out.println("The size after testing: " + chatMessageRepository.findAll().size());
    }

    @Test
    public void saveMessage() {
        //Given
        long sizeBeforeSaving = chatMessageRepository.count();
        ChatMessage dummyMessage = new ChatMessage();
        dummyMessage.setContent("dummy");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(19, 44));
        dummyMessage.setDateTime(dateTime);
        //When
        chatMessageRepository.save(dummyMessage);
        long dummyId = chatMessageRepository.findByContent("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, chatMessageRepository.count());
        //Clean up
        chatMessageRepository.deleteById(dummyId);
    }

    @Test
    public void getAllMessages() {
        //Given
        long sizeBeforeSaving = chatMessageRepository.findAll().size();
        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setContent("msg1");
        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(11, 20));
        chatMessage1.setDateTime(dateTime1);
        ChatMessage chatMessage2 = new ChatMessage();
        chatMessage2.setContent("msg2");
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.of(2020, 10, 1), LocalTime.of(21, 20));
        chatMessage2.setDateTime(dateTime2);
        //When
        chatMessageRepository.saveAll(Arrays.asList(chatMessage1, chatMessage2));
        long dummyId1 = chatMessageRepository.findByContent("msg1").getId();
        long dummyId2 = chatMessageRepository.findByContent("msg2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, chatMessageRepository.count());
        //Clean up
        chatMessageRepository.deleteById(dummyId1);
        chatMessageRepository.deleteById(dummyId2);
    }

    @Test
    public void getMessage() {
        //Given

        //When
        ChatMessage searchedMessage = chatMessageRepository.findByContent("hahhaha");
        //Then
        Assert.assertEquals("atomix", searchedMessage.getRecipient());
        //Clean up
    }

    @Test
    public void editMessage() {
        //Given
        long sizeBeforeEditing = chatMessageRepository.count();
        //When
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(20, 25));
        ChatMessage searchedMessage = chatMessageRepository.findByContent("Heyyy, what's up");
        searchedMessage.setRecipient("marqez");
        chatMessageRepository.save(searchedMessage);
        //Then
        Assert.assertEquals(sizeBeforeEditing, chatMessageRepository.count());
        Assert.assertEquals("marqez", chatMessageRepository.findByContent("Heyyy, what's up").getRecipient());
        //Clean up
    }

    @Test
    public void deleteMessage() {
        //Given
        long sizeBeforeDeleting = chatMessageRepository.count();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("dummy");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 1, 15), LocalTime.of(15, 8));
        chatMessage.setDateTime(dateTime);
        chatMessageRepository.save(chatMessage);
        //When
        long dummyId = chatMessageRepository.findByContent("dummy").getId();
        chatMessageRepository.deleteById(dummyId);
        //Then
        Assert.assertEquals(sizeBeforeDeleting, chatMessageRepository.count());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + chatMessageRepository.findAll().size());
    }

    @Test
    public void truncateTable() {
//        chatMessageRepository.deleteAll();
    }
}

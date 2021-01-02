package com.plociennik.poogphase.controller;

import com.plociennik.poogphase.exceptions.MessageNotFoundException;
import com.plociennik.poogphase.mapper.ChatMessageMapper;
import com.plociennik.poogphase.model.dto.ChatMessageDto;
import com.plociennik.poogphase.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/message")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private ChatMessageMapper chatMessageMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getMessages")
    public List<ChatMessageDto> getAllMessages() {
        return chatMessageMapper.mapToChatMessageDtoList(chatMessageService.getAllMessages());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMessage")
    public ChatMessageDto getMessage(@RequestParam Long messageId) throws MessageNotFoundException {
        return chatMessageMapper.mapToChatMessageDto(chatMessageService.getMessage(messageId).orElseThrow(MessageNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createMessage")
    public void createMessage(@RequestBody ChatMessageDto chatMessageDto) {
        chatMessageService.saveMessage(chatMessageMapper.mapToChatMessage(chatMessageDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateMessage")
    public ChatMessageDto updateMessage(@RequestBody ChatMessageDto chatMessageDto) {
        return chatMessageMapper.mapToChatMessageDto(chatMessageService.saveMessage(chatMessageMapper.mapToChatMessage(chatMessageDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteMessage")
    public void deleteMessage(@RequestParam Long messageId) {
        chatMessageService.removeMessage(messageId);
    }
}

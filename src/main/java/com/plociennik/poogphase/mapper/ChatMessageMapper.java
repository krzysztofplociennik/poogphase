package com.plociennik.poogphase.mapper;

import com.plociennik.poogphase.model.dto.ChatMessageDto;
import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChatMessageMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public ChatMessage mapToChatMessage(final ChatMessageDto chatMessageDto) {
        return new ChatMessage(
                chatMessageDto.getId(),
                userService.getUser(chatMessageDto.getAuthorId()).get(),
                chatMessageDto.getRecipient(),
                chatMessageDto.getDateTime(),
                chatMessageDto.getContent());
    }

    public ChatMessageDto mapToChatMessageDto(final ChatMessage chatMessage) {
        return new ChatMessageDto(
                chatMessage.getId(),
                chatMessage.getAuthor().getId(),
                chatMessage.getRecipient(),
                chatMessage.getDateTime(),
                chatMessage.getContent());
    }

    public List<ChatMessage> mapToChatMessageList(final List<ChatMessageDto> messages) {
        return messages.stream()
                .map(message -> new ChatMessage(
                        message.getId(),
                        userService.getUser(message.getAuthorId()).get(),
                        message.getRecipient(),
                        message.getDateTime(),
                        message.getContent()))
                .collect(Collectors.toList());
    }

    public List<ChatMessageDto> mapToChatMessageDtoList(final List<ChatMessage> messages) {
        return messages.stream()
                .map(message -> new ChatMessageDto(
                        message.getId(),
                        message.getAuthor().getId(),
                        message.getRecipient(),
                        message.getDateTime(),
                        message.getContent()))
                .collect(Collectors.toList());
    }

//    public Map<User, List<ChatMessage>> mapToChatLogMap(final Map<UserDto, List<ChatMessageDto>> mapDto) {
//        Map<User, List<ChatMessage>> map = new HashMap<>();
//        for (Map.Entry<UserDto, List<ChatMessageDto>> entry : mapDto.entrySet()) {
//            map.put(userMapper.mapToUser(entry.getKey()), mapToChatMessageSet(entry.getValue()));
//        }
//        return map;
//    }
//
//    public Map<UserDto, List<ChatMessageDto>> mapToChatLogMapDto(final Map<User, List<ChatMessage>> map) {
//        Map<UserDto, List<ChatMessageDto>> mapDto = new HashMap<>();
//        for (Map.Entry<User, List<ChatMessage>> entry : map.entrySet()) {
//            mapDto.put(userMapper.mapToUserDto(entry.getKey()), mapToChatMessageDtoSet(entry.getValue()));
//        }
//        return mapDto;
//    }
}

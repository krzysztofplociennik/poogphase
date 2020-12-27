package com.plociennik.poogphase.mapper;

import com.plociennik.poogphase.dto.ChatMessageDto;
import com.plociennik.poogphase.dto.UserDto;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ChatMessageMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public ChatMessage mapToChatMessage(final com.plociennik.poogphase.dto.ChatMessageDto chatMessageDto) {
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

    public Set<ChatMessage> mapToChatMessageSet(final Set<ChatMessageDto> messages) {
        return messages.stream()
                .map(message -> new ChatMessage(
                        message.getId(),
                        userService.getUser(message.getAuthorId()).get(),
                        message.getRecipient(),
                        message.getDateTime(),
                        message.getContent()))
                .collect(Collectors.toSet());
    }

    public Set<ChatMessageDto> mapToChatMessageDtoSet(final Set<ChatMessage> messages) {
        return messages.stream()
                .map(message -> new com.plociennik.poogphase.dto.ChatMessageDto(
                        message.getId(),
                        message.getAuthor().getId(),
                        message.getRecipient(),
                        message.getDateTime(),
                        message.getContent()))
                .collect(Collectors.toSet());
    }

    public Map<User, Set<ChatMessage>> mapToMap(final Map<UserDto, Set<ChatMessageDto>> mapDto) {
        Map<User, Set<ChatMessage>> map = new HashMap<>();
        for (Map.Entry<UserDto, Set<ChatMessageDto>> entry : mapDto.entrySet()) {
            map.put(userMapper.mapToUser(entry.getKey()), mapToChatMessageSet(entry.getValue()));
        }
        return map;
    }

    public Map<UserDto, Set<ChatMessageDto>> mapToMapDto(final Map<User, Set<ChatMessage>> map) {
        Map<UserDto, Set<ChatMessageDto>> mapDto = new HashMap<>();
        for (Map.Entry<User, Set<ChatMessage>> entry : map.entrySet()) {
            mapDto.put(userMapper.mapToUserDto(entry.getKey()), mapToChatMessageDtoSet(entry.getValue()));
        }
        return mapDto;
    }
}

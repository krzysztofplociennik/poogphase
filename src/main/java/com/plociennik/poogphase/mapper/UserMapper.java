package com.plociennik.poogphase.mapper;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatMessageMapper chatMessageMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getMail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getDateOfBirth(),
                userDto.getFriends(),
                postMapper.mapToPostSet(userDto.getPosts()),
                commentMapper.mapToCommentSet(userDto.getComments()),
                chatMessageMapper.mapToChatMessageSet(userDto.getMessages()));
//                chatMessageMapper.mapToChatLogMap(userDto.getChatLogs()));
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getFriends(),
                postMapper.mapToPostDtoSet(user.getPosts()),
                commentMapper.mapToCommentDtoSet(user.getComments()),
                chatMessageMapper.mapToChatMessageDtoSet(user.getMessages()));
//                chatMessageMapper.mapToChatLogMapDto(user.getChatLogs()));
    }

    public List<User> mapToUserList(final List<UserDto> usersDto) {
        return usersDto.stream()
                .map(userDto -> new User(
                        userDto.getId(),
                        userDto.getUsername(),
                        userDto.getPassword(),
                        userDto.getMail(),
                        userDto.getFirstName(),
                        userDto.getLastName(),
                        userDto.getDateOfBirth(),
                        userDto.getFriends(),
                        postMapper.mapToPostSet(userDto.getPosts()),
                        commentMapper.mapToCommentSet(userDto.getComments()),
                        chatMessageMapper.mapToChatMessageSet(userDto.getMessages())))
//                        chatMessageMapper.mapToChatLogMap(userDto.getChatLogs())))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getMail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getDateOfBirth(),
                        user.getFriends(),
                        postMapper.mapToPostDtoSet(user.getPosts()),
                        commentMapper.mapToCommentDtoSet(user.getComments()),
                        chatMessageMapper.mapToChatMessageDtoSet(user.getMessages())))
//                        chatMessageMapper.mapToChatLogMapDto(user.getChatLogs())))
                .collect(Collectors.toList());
    }
}

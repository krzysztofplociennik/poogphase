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
                userDto.getDateOfBirth(),
                userDto.getFriends(),
                postMapper.mapToPostList(userDto.getPosts()),
                commentMapper.mapToCommentSet(userDto.getComments()),
                chatMessageMapper.mapToChatMessageList(userDto.getMessages()));
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMail(),
                user.getDateOfBirth(),
                user.getFriends(),
                postMapper.mapToPostDtoList(user.getPosts()),
                commentMapper.mapToCommentDtoSet(user.getComments()),
                chatMessageMapper.mapToChatMessageDtoList(user.getMessages()));
    }

    public List<User> mapToUserList(final List<UserDto> usersDto) {
        return usersDto.stream()
                .map(userDto -> new User(
                        userDto.getId(),
                        userDto.getUsername(),
                        userDto.getPassword(),
                        userDto.getMail(),
                        userDto.getDateOfBirth(),
                        userDto.getFriends(),
                        postMapper.mapToPostList(userDto.getPosts()),
                        commentMapper.mapToCommentSet(userDto.getComments()),
                        chatMessageMapper.mapToChatMessageList(userDto.getMessages())))
                .collect(Collectors.toList());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getMail(),
                        user.getDateOfBirth(),
                        user.getFriends(),
                        postMapper.mapToPostDtoList(user.getPosts()),
                        commentMapper.mapToCommentDtoSet(user.getComments()),
                        chatMessageMapper.mapToChatMessageDtoList(user.getMessages())))
                .collect(Collectors.toList());
    }
}

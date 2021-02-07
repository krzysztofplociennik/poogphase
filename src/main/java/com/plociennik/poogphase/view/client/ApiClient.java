package com.plociennik.poogphase.view.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.dto.ChatMessageDto;
import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.model.dto.UserDto;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Optional.ofNullable;

@Component
public class ApiClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);
    @Value("${api.endpoint}")
    private String baseEndpoint;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private URI getAllUsersURI() {
        return UriComponentsBuilder.fromHttpUrl(baseEndpoint + "/user/getUsers")
                .build().encode().toUri();
    }

    private URI getALlPostsURI() {
        return UriComponentsBuilder.fromHttpUrl(baseEndpoint + "/post/getPosts")
                .build().encode().toUri();
    }

    private URI getAllCommentsURI() {
        return null;
    }

    private URI getAllMessagesURI() {
        return null;
    }

    public List<UserDto> getUsers() {
        try {
            UserDto[] response = restTemplate().getForObject(getAllUsersURI(), UserDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new UserDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<PostDto> getPosts() {
        try {
            PostDto[] response = restTemplate().getForObject(getALlPostsURI(), PostDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new PostDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<CommentDto> getComments() {
        try {
            CommentDto[] response = restTemplate().getForObject(getAllCommentsURI(), CommentDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new CommentDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<ChatMessageDto> getChatMessages() {
        try {
            ChatMessageDto[] response = restTemplate().getForObject(getAllMessagesURI(), ChatMessageDto[].class);
            return Arrays.asList(ofNullable(response).orElse(new ChatMessageDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void createPost(PostDto postDto) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonPost = mapper.createObjectNode();
        jsonPost.put("authorId", postDto.getAuthorId());
        jsonPost.put("dateTime", postDto.getDateTime().toString());
        jsonPost.put("content", postDto.getContent());

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonPost);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(baseEndpoint + "/post/createPost");
            StringEntity params = new StringEntity(json);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
        } finally {
            httpClient.close();
        }
    }

    public void createComment(CommentDto commentDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonPost = mapper.createObjectNode();
        jsonPost.put("authorId", commentDto.getAuthorId());
        jsonPost.put("dateTime", commentDto.getDateTime().toString());
        jsonPost.put("content", commentDto.getContent());
        jsonPost.put("postId", commentDto.getPostId());

        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonPost);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(baseEndpoint + "/comment/createComment");
            StringEntity params = new StringEntity(json);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);
        } catch (Exception ex) {
        } finally {
            httpClient.close();
        }
    }

    public void createMessage(ChatMessageDto chatMessageDto) throws IOException {

    }
}

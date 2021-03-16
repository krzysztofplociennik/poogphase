package com.plociennik.poogphase.view.logic;

import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.view.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionManager {
    private ApiClient apiClient;
    public UserDto loggedUser;

    @Autowired
    public SessionManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public UserDto getLoggedUser() {
        return apiClient.getUsers().stream()
                .filter(userDto -> userDto.getUsername().equals("dummy"))
                .findAny().get();
    }
}

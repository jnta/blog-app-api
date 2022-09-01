package com.jonata.blog.services;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.forms.UserForm;
import com.jonata.blog.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> getAllUsers();
    UserDto createUser(User user);
    UserDto updateUser(UUID id, UserForm userForm);
    Optional<UserDto> getUserById(UUID id);
    void deleteUser(UUID id);
}

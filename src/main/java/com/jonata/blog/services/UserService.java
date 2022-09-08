package com.jonata.blog.services;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.forms.UserForm;
import com.jonata.blog.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto registerUser(UserForm userForm);

    List<UserDto> getAllUsers();

    UserDto createUser(UserForm userForm);

    UserDto updateUser(Long id, UserForm userForm);

    Optional<UserDto> getUserById(Long id);

    void deleteUser(User user);
}

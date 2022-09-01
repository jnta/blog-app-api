package com.jonata.blog.services;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.forms.UserForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(UserForm userForm);

    UserDto updateUser(Long id, UserForm userForm);

    Optional<UserDto> getUserById(Long id);

    void deleteUser(Long id);
}

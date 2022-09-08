package com.jonata.blog.controllers;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.UserForm;
import com.jonata.blog.models.User;
import com.jonata.blog.repositories.UserRepository;
import com.jonata.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserForm userForm) {
        UserDto userDto = userService.createUser(userForm);
        return new ResponseEntity(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
        Optional<UserDto> userDto = userService.getUserById(id);
        if (userDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserForm userForm) {
        UserDto userDto = userService.updateUser(id, userForm);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }

}

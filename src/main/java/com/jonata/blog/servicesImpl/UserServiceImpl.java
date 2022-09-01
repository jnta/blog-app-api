package com.jonata.blog.servicesImpl;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.exceptions.SourceNotFoundException;
import com.jonata.blog.forms.UserForm;
import com.jonata.blog.models.User;
import com.jonata.blog.repositories.UserRepository;
import com.jonata.blog.services.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public  UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto createUser(User user) {
        userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID id, UserForm userForm) {
        User user = userRepository.findById(id).orElseThrow(() -> new SourceNotFoundException("User not Found!"));

        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());

        userRepository.save(user);

        return new UserDto(id, userForm);
    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id).map(UserDto::new);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}

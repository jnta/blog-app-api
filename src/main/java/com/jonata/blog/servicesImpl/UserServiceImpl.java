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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto createUser(UserForm userForm) {
        User user = userForm.convert();
        userRepository.save(user);

        return new UserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserForm userForm) {
        User user = userRepository.findById(id).orElseThrow(() -> new SourceNotFoundException("User not Found!"));

        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());

        userRepository.save(user);

        return new UserDto(id, userForm);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<UserDto> userDto = userRepository.findById(id).map(UserDto::new);
        return userDto;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

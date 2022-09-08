package com.jonata.blog.controllers;

import com.jonata.blog.dtos.UserDto;
import com.jonata.blog.forms.UserForm;
import com.jonata.blog.security.JwtTokenHelper;
import com.jonata.blog.services.UserService;
import com.jonata.blog.utils.JwtAuthRequest;
import com.jonata.blog.utils.JwtAuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody @Valid JwtAuthRequest jwtAuthRequest) {
        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserForm userForm) {
        UserDto userDto = this.userService.registerUser(userForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);

    }

    private void authenticate(String username, String password) {
        var authenticate = new UsernamePasswordAuthenticationToken(username, password);

        this.authenticationManager.authenticate(authenticate);
    }
}

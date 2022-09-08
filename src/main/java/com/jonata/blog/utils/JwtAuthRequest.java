package com.jonata.blog.utils;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class JwtAuthRequest {
    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;

}

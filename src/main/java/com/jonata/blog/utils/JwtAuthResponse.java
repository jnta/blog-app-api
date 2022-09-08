package com.jonata.blog.utils;

import com.jonata.blog.dtos.UserDto;
import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;
    private UserDto userDto;
}

package com.jonata.blog.dtos;

import com.jonata.blog.forms.UserForm;
import com.jonata.blog.models.Role;
import com.jonata.blog.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    private String about;
    private Set<Role> roles = new HashSet<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.about = user.getAbout();
        this.roles = user.getRoles();
    }

    public UserDto(Long id, UserForm userForm) {
        this.id = id;
        this.name = userForm.getName();
        this.email = userForm.getEmail();
        this.about = userForm.getAbout();
    }
}

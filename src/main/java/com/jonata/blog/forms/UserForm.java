package com.jonata.blog.forms;

import com.jonata.blog.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class UserForm {

    @NotBlank
    @Length(max = 100)
    private String name;
    @Email
    @NotBlank
    @Length(max = 255)
    private String email;
    @NotBlank
    @Length(max = 255)
    private String about;
    @NotBlank
    @Length(max = 255)
    private String password;

    public User convert() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setAbout(this.about);
        user.setPassword(this.password);

        return user;
    }
}

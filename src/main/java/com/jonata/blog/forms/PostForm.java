package com.jonata.blog.forms;

import com.jonata.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class PostForm {
    @NotBlank
    @Length(min = 3, max = 100)
    private String title;
    @NotBlank
    @Length(min = 1, max = 10000)
    private String content;
    @Length(max = 255)
    private String imageName = "default.png";

    public Post convert() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setImageName(this.imageName);
        return post;
    }

}

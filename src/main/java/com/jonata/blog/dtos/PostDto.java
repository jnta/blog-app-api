package com.jonata.blog.dtos;

import com.jonata.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Date createdOn;
    private CategoryDto category;
    private UserDto user;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
        this.category = new CategoryDto(post.getCategory());
        this.user = new UserDto(post.getUser());
    }
}

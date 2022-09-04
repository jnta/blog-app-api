package com.jonata.blog.dtos;

import com.jonata.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<CommentDto> comments = new ArrayList<>();

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdOn = post.getCreatedOn();
        this.category = new CategoryDto(post.getCategory());
        this.user = new UserDto(post.getUser());
        this.comments = post.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}

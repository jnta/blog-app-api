package com.jonata.blog.dtos;

import com.jonata.blog.models.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Long id;
    private String content;

    private String postTitle;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postTitle = comment.getPost().getTitle();
    }

}

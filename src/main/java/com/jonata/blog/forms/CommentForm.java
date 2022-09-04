package com.jonata.blog.forms;

import com.jonata.blog.models.Comment;
import com.jonata.blog.models.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class CommentForm {

    @NotBlank
    @Length(max = 1000)
    private String Content;

    public Comment convert(Post post) {
        Comment comment = new Comment();
        comment.setContent(this.Content);
        comment.setPost(post);

        return comment;
    }

}

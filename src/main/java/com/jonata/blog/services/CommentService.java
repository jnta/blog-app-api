package com.jonata.blog.services;

import com.jonata.blog.dtos.CommentDto;
import com.jonata.blog.forms.CommentForm;
import com.jonata.blog.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    CommentDto create(Post post, CommentForm commentForm);

    CommentDto getById(Long id);

    Page<CommentDto> getAll(Pageable pageable);

    List<CommentDto> getByPost(Post post);

    CommentDto update(Long id, CommentForm commentForm);

    void delete(Long id);
}

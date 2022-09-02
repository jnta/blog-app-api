package com.jonata.blog.services;

import com.jonata.blog.dtos.PostDto;
import com.jonata.blog.forms.PostForm;

import java.util.List;

public interface PostService {

    PostDto create(Long userid, Long categoryId, PostForm postForm);

    List<PostDto> getAll();

    PostDto getById(Long id);

    List<PostDto> getByUserId(Long userid);

    List<PostDto> getByCategoryId(Long categoryId);

    List<PostDto> search(String keyword);

    PostDto update(Long id, PostForm postForm);

    void delete(Long id);
}

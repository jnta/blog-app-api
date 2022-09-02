package com.jonata.blog.controllers;

import com.jonata.blog.dtos.PostDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.PostForm;
import com.jonata.blog.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<Object> create(
            @PathVariable("userId") Long userId,
            @PathVariable("categoryId") Long categoryId,
            @RequestBody @Valid PostForm postForm) {

        try {
            PostDto postDto = this.postService.create(userId, categoryId, postForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAll() {
        List<PostDto> posts = this.postService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    

}

package com.jonata.blog.controllers;

import com.jonata.blog.dtos.PostDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.PostForm;
import com.jonata.blog.services.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<PostDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        List<PostDto> posts = this.postService.getAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            PostDto posts = this.postService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<Object> getByUserId(@PathVariable("userId") Long userId) {
        try {
            List<PostDto> posts = this.postService.getByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<Object> getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        try {
            List<PostDto> posts = this.postService.getByCategoryId(categoryId);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Object> update(@PathVariable("postId") Long postId, @RequestBody @Valid PostForm postForm) {
        try {
            PostDto postDto = this.postService.update(postId, postForm);
            return ResponseEntity.status(HttpStatus.OK).body(postDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Object> delete(@PathVariable("postId") Long postId) {
        try {
            this.postService.delete(postId);
            return ResponseEntity.status(HttpStatus.OK).body("Post deleted successfully!");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

package com.jonata.blog.controllers;

import com.jonata.blog.dtos.PostDto;
import com.jonata.blog.forms.PostForm;
import com.jonata.blog.services.PostService;
import com.jonata.blog.utils.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> create(
            @PathVariable("userId") Long userId,
            @PathVariable("categoryId") Long categoryId,
            @RequestBody @Valid PostForm postForm) {

        PostDto postDto = this.postService.create(userId, categoryId, postForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);

    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PostDto> posts = this.postService.getAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable("id") Long id) {
        PostDto posts = this.postService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getByUserId(@PathVariable("userId") Long userId) {
        List<PostDto> posts = this.postService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<PostDto> posts = this.postService.getByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> posts = this.postService.search(keywords);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> update(@PathVariable("postId") Long postId, @RequestBody @Valid PostForm postForm) {

        PostDto postDto = this.postService.update(postId, postForm);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("postId") Long postId) {
        this.postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Post deleted successfully!", true));
    }
}

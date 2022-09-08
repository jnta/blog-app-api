package com.jonata.blog.controllers;

import com.jonata.blog.dtos.CommentDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.CommentForm;
import com.jonata.blog.models.Post;
import com.jonata.blog.repositories.PostRepository;
import com.jonata.blog.services.CommentService;
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

@RestController("/api/v1")
public class CommentController {

    private final CommentService commentService;
    private final PostRepository postRepository;

    public CommentController(CommentService commentService, PostRepository postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("postId") Long postId, @RequestBody @Valid CommentForm commentForm) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
        CommentDto commentDto = this.commentService.create(post, commentForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> getById(@PathVariable("commentId") Long commentId) {
        CommentDto commentDto = this.commentService.getById(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @GetMapping("/comments")
    public ResponseEntity<Page<CommentDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommentDto> pageComments = this.commentService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageComments);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getByPost(@PathVariable("postId") Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
        List<CommentDto> comments = this.commentService.getByPost(post);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> update(@PathVariable("commentId") Long commentId, @RequestBody @Valid CommentForm commentForm) {
        CommentDto commentDto = this.commentService.update(commentId, commentForm);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("commentId") Long commentId) {
        this.commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment deleted successfully!", true));
    }
}

package com.jonata.blog.controllers;

import com.jonata.blog.dtos.CommentDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.CommentForm;
import com.jonata.blog.models.Post;
import com.jonata.blog.repositories.PostRepository;
import com.jonata.blog.services.CommentService;
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
    public ResponseEntity<Object> create(@PathVariable("postId") Long postId, @RequestBody @Valid CommentForm commentForm) {
        try {
            Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
            CommentDto commentDto = this.commentService.create(post, commentForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Object> getById(@PathVariable("commentId") Long commentId) {
        try {
            CommentDto commentDto = this.commentService.getById(commentId);
            return ResponseEntity.status(HttpStatus.OK).body(commentDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<Page<CommentDto>> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommentDto> pageComments = this.commentService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageComments);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<Object> getByPost(@PathVariable("postId") Long postId) {
        try {
            Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
            List<CommentDto> comments = this.commentService.getByPost(post);
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Object> update(@PathVariable("commentId") Long commentId, @RequestBody @Valid CommentForm commentForm) {
        try {
            CommentDto commentDto = this.commentService.update(commentId, commentForm);
            return ResponseEntity.status(HttpStatus.OK).body(commentDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Object> delete(@PathVariable("commentId") Long commentId) {
        try {
            this.commentService.delete(commentId);
            return ResponseEntity.status(HttpStatus.OK).body("Comment deleted successfully!");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

package com.jonata.blog.servicesImpl;

import com.jonata.blog.dtos.CommentDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.CommentForm;
import com.jonata.blog.models.Comment;
import com.jonata.blog.models.Post;
import com.jonata.blog.repositories.CommentRepository;
import com.jonata.blog.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public CommentDto create(Post post, CommentForm commentForm) {
        Comment comment = commentForm.convert(post);
        this.commentRepository.save(comment);

        return new CommentDto(comment);
    }

    @Override
    public CommentDto getById(Long id) {
        return this.commentRepository.findById(id).map(CommentDto::new).orElseThrow(() -> new ResourceNotFoundException("Comment not found!"));
    }

    @Override
    public Page<CommentDto> getAll(Pageable pageable) {
        return this.commentRepository.findAll(pageable).map(CommentDto::new);
    }

    @Override
    public List<CommentDto> getByPost(Post post) {
        return this.commentRepository.findByPost(post).stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto update(Long id, CommentForm commentForm) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found!"));
        comment.setContent(commentForm.getContent());
        this.commentRepository.save(comment);

        return new CommentDto(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found!"));
        this.commentRepository.delete(comment);
    }
}

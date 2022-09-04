package com.jonata.blog.repositories;

import com.jonata.blog.models.Comment;
import com.jonata.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}

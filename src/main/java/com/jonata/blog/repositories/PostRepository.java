package com.jonata.blog.repositories;

import com.jonata.blog.models.Category;
import com.jonata.blog.models.Post;
import com.jonata.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("SELECT p FROM Post p where p.title like :keyword")
    List<Post> searchByTitle(@Param("keyword") String title);
}

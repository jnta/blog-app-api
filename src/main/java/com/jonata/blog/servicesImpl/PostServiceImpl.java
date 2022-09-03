package com.jonata.blog.servicesImpl;

import com.jonata.blog.dtos.PostDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.PostForm;
import com.jonata.blog.models.Category;
import com.jonata.blog.models.Post;
import com.jonata.blog.models.User;
import com.jonata.blog.repositories.CategoryRepository;
import com.jonata.blog.repositories.PostRepository;
import com.jonata.blog.repositories.UserRepository;
import com.jonata.blog.services.CategoryService;
import com.jonata.blog.services.PostService;
import com.jonata.blog.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, UserService userService, CategoryService categoryService, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public PostDto create(Long userid, Long categoryId, PostForm postForm) {
        User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        Post post = postForm.convert();
        post.setUser(user);
        post.setCategory(category);

        postRepository.save(post);

        return new PostDto(post);
    }

    @Override
    public Page<PostDto> getAll(Pageable pageable) {
        Page<PostDto> pagePosts = this.postRepository.findAll(pageable).map(content -> new PostDto(content));

        return pagePosts;
    }

    @Override
    public PostDto getById(Long id) {
        return postRepository.findById(id).map(PostDto::new).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
    }

    @Override
    public List<PostDto> getByUserId(Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        return postRepository.findByUser(user).stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        return postRepository.findByCategory(category).stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> search(String keyword) {
        List<Post> posts = this.postRepository.searchByTitle("%" + keyword + "%");
        return posts.stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDto update(Long id, PostForm postForm) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
        post.setTitle(post.getTitle());
        post.setContent(postForm.getContent());
        post.setImageName(postForm.getImageName());

        postRepository.save(post);
        return new PostDto(post);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found!"));
        postRepository.delete(post);
    }
}

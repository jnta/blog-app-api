package com.jonata.blog.servicesImpl;

import com.jonata.blog.dtos.CategoryDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.CategoryForm;
import com.jonata.blog.models.Category;
import com.jonata.blog.repositories.CategoryRepository;
import com.jonata.blog.services.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryDto create(CategoryForm categoryForm) {
        Category category = categoryForm.convert();
        categoryRepository.save(category);

        return new CategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryRepository.findById(id).map(CategoryDto::new).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    @Transactional
    public CategoryDto update(Long id, CategoryForm categoryForm) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        category.setTitle(categoryForm.getTitle());
        category.setDescription(categoryForm.getDescription());
        categoryRepository.save(category);

        return new CategoryDto(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        categoryRepository.delete(category);
    }
}

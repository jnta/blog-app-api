package com.jonata.blog.services;

import com.jonata.blog.dtos.CategoryDto;
import com.jonata.blog.forms.CategoryForm;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryForm categoryForm);

    List<CategoryDto> getAll();

    CategoryDto getById(Long id);

    CategoryDto update(Long id, CategoryForm categoryForm);

    void delete(Long id);

}

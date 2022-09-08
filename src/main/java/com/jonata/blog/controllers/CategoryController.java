package com.jonata.blog.controllers;

import com.jonata.blog.dtos.CategoryDto;
import com.jonata.blog.forms.CategoryForm;
import com.jonata.blog.services.CategoryService;
import com.jonata.blog.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryForm categoryForm) {
        CategoryDto categoryDto = categoryService.create(categoryForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") Long id) {
        CategoryDto categoryDto = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryForm categoryForm) {
        CategoryDto categoryDto = categoryService.update(id, categoryForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Category deleted successfully!", true));
    }

}

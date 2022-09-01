package com.jonata.blog.controllers;

import com.jonata.blog.dtos.CategoryDto;
import com.jonata.blog.exceptions.ResourceNotFoundException;
import com.jonata.blog.forms.CategoryForm;
import com.jonata.blog.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
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
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            CategoryDto categoryDto = categoryService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryForm categoryForm) {
        try {
            CategoryDto categoryDto = categoryService.update(id, categoryForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully!");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

package com.jonata.blog.dtos;

import com.jonata.blog.models.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long categoryId;
    private String categoryTitle;
    private String categoryDescription;

    public CategoryDto(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryTitle = category.getCategoryTitle();
        this.categoryDescription = category.getCategoryDescription();
    }
}

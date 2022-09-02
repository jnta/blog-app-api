package com.jonata.blog.dtos;

import com.jonata.blog.models.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String title;
    private String description;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.description = category.getDescription();
    }
}

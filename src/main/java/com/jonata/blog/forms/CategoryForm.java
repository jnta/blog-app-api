package com.jonata.blog.forms;

import com.jonata.blog.models.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class CategoryForm {
    @NotBlank
    @Length(max = 100)
    private String title;
    private String description;

    public Category convert() {
        Category category = new Category();
        category.setTitle(this.title);
        category.setDescription(this.description);

        return category;
    }
}

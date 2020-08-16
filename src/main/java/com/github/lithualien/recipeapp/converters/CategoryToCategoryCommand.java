package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.CategoryCommand;
import com.github.lithualien.recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    public CategoryCommand convert(Category category) {
        return null;
    }

}

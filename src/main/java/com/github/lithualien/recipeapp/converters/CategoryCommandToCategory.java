package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.CategoryCommand;
import com.github.lithualien.recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand categoryCommand) {
        return null;
    }

}

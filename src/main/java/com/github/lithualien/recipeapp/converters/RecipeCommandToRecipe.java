package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes noteConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes noteConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        return null;
    }

}

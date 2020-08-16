package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public RecipeCommand convert(Recipe recipe) {
        return null;
    }

}

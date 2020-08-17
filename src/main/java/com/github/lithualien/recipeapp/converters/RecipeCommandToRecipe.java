package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Category;
import com.github.lithualien.recipeapp.domain.Ingredient;
import com.github.lithualien.recipeapp.domain.Notes;
import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.domain.enums.Difficulty;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source == null) {
            return null;
        }

        final Notes notes = notesConverter.convert(source.getNotesCommand());
        final Recipe recipe = new Recipe();
        Difficulty difficulty;

        if (source.getDifficulty() == null) {
            difficulty = Difficulty.EASY;
        } else {
            difficulty = source.getDifficulty();
        }

        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(difficulty);
        recipe.setNotes(notes);

        if ((source.getCategoryCommands() != null) && (source.getCategoryCommands().size() > 0)) {
            source.getCategoryCommands()
                    .forEach(categoryCommand -> {
                        Category category = categoryConverter.convert(categoryCommand);
                        recipe.getCategories().add(category);
                    });

        }

        if ((source.getIngredientCommands() != null) && (source.getIngredientCommands().size() > 0)) {
            source.getIngredientCommands()
                    .forEach(ingredientCommand -> {
                        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);
                        recipe.getIngredients().add(ingredient);
                    });
        }

        return recipe;
    }

}

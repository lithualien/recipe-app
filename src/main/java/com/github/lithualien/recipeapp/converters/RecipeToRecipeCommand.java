package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.CategoryCommand;
import com.github.lithualien.recipeapp.commands.IngredientCommand;
import com.github.lithualien.recipeapp.commands.NotesCommand;
import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Ingredient;
import com.github.lithualien.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
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

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source)  {
        if (source == null) {
            return null;
        }

        final NotesCommand notesCommand = notesConverter.convert(source.getNotes());
        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotesCommand(notesCommand);

        if ((source.getCategories() != null) && ( source.getCategories().size() > 0)) {
            source.getCategories()
                    .forEach(category -> {
                        CategoryCommand categoryCommand = categoryConverter.convert(category);
                        recipeCommand.getCategoryCommands().add(categoryCommand);
                    });
        }

        if ((source.getIngredients() != null) && (source.getIngredients().size() > 0)) {
            source.getIngredients()
                    .forEach(ingredient -> {
                        IngredientCommand ingredientCommand = new IngredientCommand();
                        recipeCommand.getIngredientCommands().add(ingredientCommand);
                    });
        }

        return recipeCommand;

    }

}

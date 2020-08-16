package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.CategoryCommand;
import com.github.lithualien.recipeapp.commands.IngredientCommand;
import com.github.lithualien.recipeapp.commands.NotesCommand;
import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.domain.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long RECIPE_ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final Integer PREP_TIME = Integer.valueOf("5");
    private static final Integer COOK_TIME = Integer.valueOf("10");
    private static final Integer SERVINGS = Integer.valueOf("15");
    private static final String SOURCE = "Source";
    private static final String URL = "Url";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long CATEGORY_ID_1 = 2L;
    private static final Long CATEGORY_ID_2 = 3L;
    private static final Long INGREDIENT_ID_1 = 4L;
    private static final Long INGREDIENT_ID_2 = 5L;
    private static final Long NOTES_ID = 6L;
    private RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory());
    }

    @Test
    void testConvert() {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        Set<CategoryCommand> categories = new HashSet<>();

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_ID_1);
        categories.add(categoryCommand1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_ID_2);
        categories.add(categoryCommand2);

        recipeCommand.setCategoryCommands(categories);

        Set<IngredientCommand> ingredientCommands = new HashSet<>();

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_ID_1);
        ingredientCommands.add(ingredientCommand1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_ID_2);
        ingredientCommands.add(ingredientCommand2);

        recipeCommand.setIngredientCommands(ingredientCommands);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);

        recipeCommand.setNotesCommand(notesCommand);

        // when
        Recipe recipe = converter.convert(recipeCommand);

        // then
        assertNotNull(recipe);
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredients());
        assertNotNull(recipe.getNotes());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

}
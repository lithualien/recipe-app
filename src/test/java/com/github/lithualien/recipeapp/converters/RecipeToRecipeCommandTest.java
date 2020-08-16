package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Category;
import com.github.lithualien.recipeapp.domain.Ingredient;
import com.github.lithualien.recipeapp.domain.Notes;
import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.domain.enums.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeToRecipeCommandTest {

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
    private RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand()
        );
    }

    @Test
    void convert() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Set<Category> categories = new HashSet<>();

        Category category1 = new Category();
        category1.setId(CATEGORY_ID_1);
        categories.add(category1);

        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);
        categories.add(category2);

        recipe.setCategories(categories);

        Set<Ingredient> ingredients = new HashSet<>();

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_ID_1);
        ingredients.add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);
        ingredients.add(ingredient2);

        recipe.setIngredients(ingredients);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        // when
        RecipeCommand recipeCommand = converter.convert(recipe);

        // then
        assertNotNull(recipeCommand);
        assertNotNull(recipeCommand.getCategoryCommands());
        assertNotNull(recipeCommand.getIngredientCommands());
        assertNotNull(recipeCommand.getNotesCommand());
        assertEquals(RECIPE_ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(2, recipeCommand.getCategoryCommands().size());
        assertEquals(2, recipeCommand.getIngredientCommands().size());
        assertEquals(NOTES_ID, recipeCommand.getNotesCommand().getId());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }
}
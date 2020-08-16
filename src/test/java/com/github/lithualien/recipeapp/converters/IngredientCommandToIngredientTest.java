package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.IngredientCommand;
import com.github.lithualien.recipeapp.commands.UnitOfMeasureCommand;
import com.github.lithualien.recipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    private static final Long INGREDIENT_ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final BigDecimal AMOUNT = new BigDecimal("10");
    private static final Long UOM_ID = 2L;
    private IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testConvert() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);

        ingredientCommand.setUnitOfMeasureCommand(uomCommand);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    void testConvertWithNullUOM() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setUnitOfMeasureCommand(null);

        // when
        Ingredient ingredient = converter.convert(ingredientCommand);

        // then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

}
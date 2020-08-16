package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.IngredientCommand;
import com.github.lithualien.recipeapp.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        return null;
    }

}

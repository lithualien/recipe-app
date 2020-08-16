package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.UnitOfMeasureCommand;
import com.github.lithualien.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        return null;
    }

}

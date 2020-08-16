package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.UnitOfMeasureCommand;
import com.github.lithualien.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testConvert() {
        // given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        // then
        assertNotNull(uomCommand);
        assertEquals(ID, uomCommand.getId());
        assertEquals(DESCRIPTION, uomCommand.getDescription());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

}
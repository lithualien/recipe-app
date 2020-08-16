package com.github.lithualien.recipeapp.converters;

import com.github.lithualien.recipeapp.commands.NotesCommand;
import com.github.lithualien.recipeapp.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Override
    public Notes convert(NotesCommand notesCommand) {
        return null;
    }

}

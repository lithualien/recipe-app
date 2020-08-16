package com.github.lithualien.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand implements Serializable {

    private Long id;
    private String recipeNotes;

}

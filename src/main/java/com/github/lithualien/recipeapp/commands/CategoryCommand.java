package com.github.lithualien.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand implements Serializable {

    private Long id;
    private String description;

}

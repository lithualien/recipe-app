package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand save(RecipeCommand recipeCommand);
}

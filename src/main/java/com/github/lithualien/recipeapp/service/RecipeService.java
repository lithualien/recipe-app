package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I am in the service");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository
                .findAll()
                .iterator()
                .forEachRemaining(recipes::add);
        return recipes;
    }
}

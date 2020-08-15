package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
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

        log.debug("Returned recipes.");

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository
                .findAll()
                .iterator()
                .forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        log.debug("Returning recipe id = " + id);
        return recipeRepository
                .findById(id)
                .<NoSuchElementException>orElseThrow(() -> {
                    throw new NoSuchElementException("Recipe with id = " + id + " does not exist.");
                });
    }
}

package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    private IndexController indexController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        // given
        String indexPage = "index";
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipes.add(recipe);
        recipes.add(recipe1);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        when(recipeService.getRecipes()).thenReturn(recipes);

        // then
        assertEquals(indexPage, indexController.getIndexPage(model));
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        verify(recipeService, times(1)).getRecipes();
        Set<Recipe> recipeSet = argumentCaptor.getValue();
        assertEquals(2, recipeSet.size());
    }
}
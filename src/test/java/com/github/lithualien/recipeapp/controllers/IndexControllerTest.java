package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;

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
        String indexPage = "index";

        assertEquals(indexPage, indexController.getIndexPage(model));

        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(recipeService, times(1)).getRecipes();
    }
}
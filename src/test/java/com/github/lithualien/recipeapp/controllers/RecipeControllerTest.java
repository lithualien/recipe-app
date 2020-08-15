package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import com.github.lithualien.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.*;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    private RecipeRepository controller;

    @InjectMocks
    private RecipeService service;

    private MockMvc mockmvc;


    @BeforeEach
    void setUp() {
        mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getIndexPage() throws Exception  {
        Long id1 = 1L;
        Long id2 = 2L;

        Recipe recipe = new Recipe();
        recipe.setId(id1);

        Recipe recipe2 = new Recipe();
        recipe2.setId(id2);

        Set<Recipe> recipeSet = new HashSet<>();

        recipeSet.add(recipe);
        recipeSet.add(recipe2);

        when(service.getRecipes()).thenReturn(recipeSet);

        mockmvc
                .perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/index"))
                .andExpect(model().attribute("recipes", hasSize(2)));
    }

    @Test
    public void getShowPage() throws Exception {
        Long id = 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(service.findById(anyLong())).thenReturn(recipe);

        mockmvc
                .perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes/show"))
                .andExpect(model().attribute("recipe", hasSize(1)));
    }
}
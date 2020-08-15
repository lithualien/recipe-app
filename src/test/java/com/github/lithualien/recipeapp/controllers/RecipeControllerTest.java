package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.repository.RecipeRepository;
import com.github.lithualien.recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

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
    void getIndexPage() {

    }
}
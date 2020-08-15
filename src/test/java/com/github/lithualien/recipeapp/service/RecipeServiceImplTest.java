package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private static final Long ID = 1L;

    @InjectMocks
    private RecipeServiceImpl service;

    @Mock
    private RecipeRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(repository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = service.getRecipes();

        assertEquals(recipes.size(), 1);

        verify(repository, times(1)).findAll();
    }

    @Test
    void findById() {
        Recipe returnedRecipe = new Recipe();
        returnedRecipe.setId(ID);

        when(repository.findById(anyLong())).thenReturn(Optional.of(returnedRecipe));

        Recipe recipe = service.findById(ID);

        assertNotNull(recipe.getId());
        assertEquals(ID, recipe.getId());
    }

    @Test
    void findByIdNotFound() {
        assertThrows(NoSuchElementException.class, () -> service.findById(ID));
    }
}
package com.github.lithualien.recipeapp.service;

import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Description";
    private static final Integer PREP_TIME = Integer.valueOf("5");

    @Mock
    private RecipeRepository repository;

    @InjectMocks
    private RecipeServiceImpl service;

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

    @Test
    void save() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);

        // when
        when(repository.save(recipe)).thenReturn(recipe);
        Recipe savedRecipe = service.save(recipe);

        // then
        assertNotNull(savedRecipe);
        assertEquals(ID, savedRecipe);
        assertEquals(DESCRIPTION, savedRecipe.getDescription());
        assertEquals(PREP_TIME, savedRecipe.getPrepTime());

    }
}
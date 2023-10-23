package com.udemy.recipeapp.services;

import com.udemy.recipeapp.converters.RecipeCommandToRecipe;
import com.udemy.recipeapp.converters.RecipeToRecipeCommand;
import com.udemy.recipeapp.exceptions.NotFoundException;
import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Executable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        // Tells Mockito to give a mock RecipeRepository
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {
        // Given
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // When
        NotFoundException notFoundException = assertThrows(
                NotFoundException.class, ()->recipeService.findById(1L),
                "Expected exception to throw an error. But it didn't"
        );

        // Then
        assertTrue(notFoundException.getMessage().contains("Recipe not found"));
    }

    @Test
    void getRecipesTest() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        // Below to check there's only one call to the repo
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void TestDeleteByID() {
        // given
        Long idToDelete = Long.valueOf(2L);
        // when
        recipeService.deleteRecipe(idToDelete);
        // no 'when' since method has void return

        // then
        verify(recipeRepository,times(1)).deleteById(anyLong());
    }
}
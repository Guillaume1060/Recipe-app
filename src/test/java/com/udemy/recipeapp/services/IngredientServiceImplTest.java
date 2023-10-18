package com.udemy.recipeapp.services;

import com.udemy.recipeapp.commands.IngredientCommand;
import com.udemy.recipeapp.converters.IngredientCommandToIngredient;
import com.udemy.recipeapp.converters.IngredientToIngredientCommand;
import com.udemy.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.udemy.recipeapp.model.Ingredient;
import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.repositories.IngredientRepository;
import com.udemy.recipeapp.repositories.RecipeRepository;
import com.udemy.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientService ingredientService;
    @Mock
    IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    IngredientRepository ingredientRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Tells Mockito to give a mock RecipeRepository
        MockitoAnnotations.openMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, ingredientRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

//    @Test
//    public void testSaveRecipeCommand() throws Exception {
//        //given
//        IngredientCommand command = new IngredientCommand();
//        command.setId(3L);
//        command.setRecipeId(2L);
//
//        Optional<Recipe> recipeOptional = Optional.of(new Recipe());
//
//        Recipe savedRecipe = new Recipe();
//        savedRecipe.addIngredient(new Ingredient());
//        savedRecipe.getIngredients().iterator().next().setId(3L);
//
//        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
//        when(recipeRepository.save(any())).thenReturn(savedRecipe);
//
//        //when
//        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
//
//        //then
//        assertEquals(Long.valueOf(3L), savedCommand.getId());
//        verify(recipeRepository, times(1)).findById(anyLong());
//        verify(recipeRepository, times(1)).save(any(Recipe.class));
//    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        ingredientService.deleteById(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

}

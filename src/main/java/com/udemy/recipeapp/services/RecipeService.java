package com.udemy.recipeapp.services;

import com.udemy.recipeapp.commands.RecipeCommand;
import com.udemy.recipeapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand findCommandById(Long L);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteRecipe(Long L);


}

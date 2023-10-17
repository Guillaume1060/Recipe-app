package com.udemy.recipeapp.services;



import com.udemy.recipeapp.commands.IngredientCommand;
import com.udemy.recipeapp.model.Ingredient;

import java.util.Set;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId,Long IngredientId);
}

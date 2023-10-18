package com.udemy.recipeapp.services;



import com.udemy.recipeapp.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long RecipeId,Long IngredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(Long recipeId,Long IdToDelete);
}

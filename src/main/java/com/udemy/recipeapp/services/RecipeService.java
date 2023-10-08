package com.udemy.recipeapp.services;

import com.udemy.recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}

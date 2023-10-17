package com.udemy.recipeapp.repositories;

import com.udemy.recipeapp.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}

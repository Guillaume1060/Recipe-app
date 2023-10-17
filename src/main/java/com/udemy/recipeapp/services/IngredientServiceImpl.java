package com.udemy.recipeapp.services;

import com.udemy.recipeapp.commands.IngredientCommand;
import com.udemy.recipeapp.converters.IngredientToIngredientCommand;
import com.udemy.recipeapp.model.Ingredient;
import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isEmpty()) {
            // @Todo impl error handling
            log.debug("recipe not found");
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> optionalIngredient = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (optionalIngredient.isEmpty()) {
            // @Todo impl error handling
            log.debug("Ingredient not found");
        }
        return optionalIngredient.get();
    }
}

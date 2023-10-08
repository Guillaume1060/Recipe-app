package com.udemy.recipeapp.repositories;

import com.udemy.recipeapp.model.Category;
import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
    
}

package com.udemy.recipeapp.repositories;

import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
    
}

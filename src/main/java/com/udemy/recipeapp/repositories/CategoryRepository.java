package com.udemy.recipeapp.repositories;

import com.udemy.recipeapp.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    
}

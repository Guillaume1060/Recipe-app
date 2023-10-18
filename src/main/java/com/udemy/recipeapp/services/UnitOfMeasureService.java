package com.udemy.recipeapp.services;

import com.udemy.recipeapp.commands.UnitOfMeasureCommand;


import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}

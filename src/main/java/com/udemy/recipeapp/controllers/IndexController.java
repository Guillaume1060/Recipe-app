package com.udemy.recipeapp.controllers;

import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
@Slf4j
@Controller
public class IndexController {
    private final RecipeServiceImpl recipeService;

    public IndexController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {
        log.info("I'm in controller GetIndexPage");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}

package com.udemy.recipeapp.controllers;

import com.udemy.recipeapp.commands.RecipeCommand;
import com.udemy.recipeapp.services.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class RecipeController {
    private final RecipeServiceImpl recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id,Model model) {
        log.info("I'm in controller Recipe");
        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe",new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id,Model model) {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe/")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        log.debug("Deleting id "+id);
        recipeService.deleteRecipe(Long.valueOf(id));
        return "redirect:/";
    }

}

package com.udemy.recipeapp.controllers;

import com.udemy.recipeapp.commands.RecipeCommand;
import com.udemy.recipeapp.model.Recipe;
import com.udemy.recipeapp.services.RecipeService;

import com.udemy.recipeapp.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @Mock
    RecipeServiceImpl recipeService;
    @InjectMocks
    RecipeController controller;
    Recipe recipe;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setId(1L);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
//                @todo Check model attributes below
//                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        RecipeCommand command = new RecipeCommand();

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

//    @TODO check these tests.
//    @Test
//    public void testPostNewRecipeForm() throws Exception {
//        RecipeCommand command = new RecipeCommand();
//        command.setId(2L);
//
//        when(recipeService.saveRecipeCommand(any())).thenReturn(command);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/recipe")
//                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
////                              .param("id", "")
////                              .param("description", "some string")
//                )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/recipe/2/show"));
//    }

//    @Test
//    public void testGetUpdateView() throws Exception {
//        RecipeCommand command = new RecipeCommand();
//        command.setId(2L);
//
//        when(recipeService.findCommandById(anyLong())).thenReturn(command);
//
//        mockMvc.perform(get("/recipe/1/update"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("recipe/recipeform"))
//                .andExpect(model().attributeExists("recipe"));
//    }

    @Test
    public void testDeleteAction () throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(recipeService,times(1)).deleteRecipe(anyLong());
    }
}


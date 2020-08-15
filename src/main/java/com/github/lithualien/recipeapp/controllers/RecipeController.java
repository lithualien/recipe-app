package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @RequestMapping( {"", "/", "/index"} )
    public String getIndexPage(Model model) {

        log.debug("Getting index page.");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "recipes/index";
    }

    @RequestMapping( {"/show/{id}"})
    public String showById(@PathVariable("id") Long id, Model model) {

        log.debug("Getting recipe id = " + id);

        model.addAttribute("recipe", recipeService.findById(id));
        return "recipes/show";
    }
}

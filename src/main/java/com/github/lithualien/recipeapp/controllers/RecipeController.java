package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.commands.RecipeCommand;
import com.github.lithualien.recipeapp.domain.Recipe;
import com.github.lithualien.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @GetMapping( {"", "/", "/index"} )
    public String getIndexPage(Model model) {

        log.debug("Getting index page.");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "recipes/recipe-index";
    }

    @GetMapping( {"/{id}/show"} )
    public String showById(@PathVariable("id") Long id, Model model) {

        log.debug("Getting recipe id = " + id);

        model.addAttribute("recipe", recipeService.findById(id));
        return "recipes/show-recipe";
    }

    @GetMapping( {"/new"} )
    public String showRecipeForm(Model model) {

        log.debug("Loaded new recipe form.");

        model.addAttribute("recipe", new RecipeCommand());

        return "recipes/recipe-form";
    }

    @PostMapping
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {

        RecipeCommand savedRecipeCommand = recipeService.save(recipeCommand);

        log.debug("Saved new recipe of id " + savedRecipeCommand.getId());

        return "redirect:/recipes/" + savedRecipeCommand.getId() + "/show/";
    }

    @RequestMapping("/{id}/update")
    public String updateRecipe(@PathVariable("id") Long id, Model model) {
        RecipeCommand recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/recipe-form";
    }

}

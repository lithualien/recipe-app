package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.domain.Category;
import com.github.lithualien.recipeapp.domain.UnitOfMeasure;
import com.github.lithualien.recipeapp.repository.CategoryRepository;
import com.github.lithualien.recipeapp.repository.RecipeRepository;
import com.github.lithualien.recipeapp.repository.UnitOfMeasureRepository;
import com.github.lithualien.recipeapp.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {

        this.recipeService = recipeService;
    }

    @RequestMapping( {"", "/", "/index"} )
    public String getIndexPage(Model model) {

        log.debug("Getting index page.");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }



}

package com.github.lithualien.recipeapp.controllers;

import com.github.lithualien.recipeapp.domain.Category;
import com.github.lithualien.recipeapp.domain.UnitOfMeasure;
import com.github.lithualien.recipeapp.repository.CategoryRepository;
import com.github.lithualien.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository uomRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
    }

    @RequestMapping( {"", "/", "/index"} )
    public String getIndexPage() {

        Optional<Category> categoryOptional =
                categoryRepository.findByDescription("American");

        Optional<UnitOfMeasure> uomOptional =
                uomRepository.findByDescription("Teaspoon");

        System.out.println("Cat id is: " + categoryOptional.get().getId());

        System.out.println("UOM id is: " + uomOptional.get().getId());

        return "index";
    }



}

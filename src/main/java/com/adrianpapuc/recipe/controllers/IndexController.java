package com.adrianpapuc.recipe.controllers;

import com.adrianpapuc.recipe.domain.Category;
import com.adrianpapuc.recipe.domain.UnitOfMeasure;
import com.adrianpapuc.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String index(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}

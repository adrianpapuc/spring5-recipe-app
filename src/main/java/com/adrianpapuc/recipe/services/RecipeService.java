package com.adrianpapuc.recipe.services;

import com.adrianpapuc.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}

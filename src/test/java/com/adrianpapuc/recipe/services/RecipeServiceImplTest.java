package com.adrianpapuc.recipe.services;

import com.adrianpapuc.recipe.domain.Recipe;
import com.adrianpapuc.recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        Recipe recipe2 = new Recipe();
        HashSet recipesData = new HashSet();
        recipe.setId(new Long(123l));
        recipe2.setId(new Long(321l));
        recipesData.add(recipe);
        recipesData.add(recipe2);

        when(recipeRepository.findAll()).thenReturn(recipesData);


        Set<Recipe> recipes = recipeService.getRecipes();
        recipes.forEach(recipe1 -> System.out.println(recipe1.getId()));
        assertEquals(2,recipes.size());
        verify(recipeRepository,times(1)).findAll();
    }
}
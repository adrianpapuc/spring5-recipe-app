package com.adrianpapuc.recipe.repositories;

import com.adrianpapuc.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}

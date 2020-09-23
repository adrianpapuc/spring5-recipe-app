package com.adrianpapuc.recipe.repositories;

import com.adrianpapuc.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}

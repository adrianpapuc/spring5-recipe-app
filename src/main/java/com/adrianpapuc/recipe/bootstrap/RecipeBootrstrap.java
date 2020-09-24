package com.adrianpapuc.recipe.bootstrap;


import com.adrianpapuc.recipe.domain.*;
import com.adrianpapuc.recipe.repositories.CategoryRepository;
import com.adrianpapuc.recipe.repositories.RecipeRepository;
import com.adrianpapuc.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootrstrap implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootrstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){
        List<Recipe> recipes = new ArrayList<>();

        //Get UOM's

        RuntimeException missingUom = new RuntimeException("UOM Missing");

        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()){
            throw missingUom;
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tablespoonUomOptional.isPresent()){
            throw missingUom;
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaspoonUomOptional.isPresent()){
            throw missingUom;
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()){
            throw missingUom;
        }
        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()){
            throw missingUom;
        }
        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cups");
        if (!cupsUomOptional.isPresent()){
            throw missingUom;
        }

        Optional<UnitOfMeasure> poundsUomOptional = unitOfMeasureRepository.findByDescription("Pounds");
        if (!poundsUomOptional.isPresent()){
            throw missingUom;
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure pundsUom = pintUomOptional.get();

        //get categories
        RuntimeException missingCategory = new RuntimeException("Category missing");
        Optional<Category> americanCatOptional = categoryRepository.findByDescription("American");
        if (!americanCatOptional.isPresent()){
            throw missingCategory;
        }
        Optional<Category> mexicanCatOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCatOptional.isPresent()){
            throw missingCategory;
        }

        Category americanCategory = americanCatOptional.get();
        Category mexicanCategory = mexicanCatOptional.get();

        //Guacamole recipe
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setServings(4);
        guacamole.setDificulty(Dificulty.EASY);
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        guacamole.setNotes(guacNotes);
        guacamole.setSource("Simply Recipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve."
        );

        guacamole.addIngredient(new Ingredient("rpie avocados", new BigDecimal(2), eachUom));
        guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaspoonUom));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacamole.addIngredient(new Ingredient("freshly grated black papper", new BigDecimal(2), dashUom));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacamole.getCategories().add(mexicanCategory);

        recipes.add(guacamole);

        //Taco recipe

        Recipe tacos = new Recipe();
        tacos.setDescription("Griled chicken tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(9);
        tacos.setServings(4);
        tacos.setDificulty(Dificulty.MODERATE);
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        tacos.setNotes(tacoNotes);
        tacos.setSource("Simply Recipes");
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "Spicy Grilled Chicken Tacos\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        );

        tacos.addIngredient(new Ingredient("ancho chili poder", new BigDecimal(2),tablespoonUom));
        tacos.addIngredient(new Ingredient("dried oregano", new BigDecimal(1),tablespoonUom));
        tacos.addIngredient(new Ingredient("dried cumin", new BigDecimal(1),tablespoonUom));
        tacos.addIngredient(new Ingredient("sugar", new BigDecimal(1),tablespoonUom));
        tacos.addIngredient(new Ingredient("salt", new BigDecimal(".5"),tablespoonUom));
        tacos.addIngredient(new Ingredient("finely chopped garlic", new BigDecimal(1),eachUom));
        tacos.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1),tablespoonUom));
        tacos.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3),tablespoonUom));
        tacos.addIngredient(new Ingredient("olive oil", new BigDecimal(2),tablespoonUom));
        tacos.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal("1.25"),pundsUom));

        tacos.getCategories().add(mexicanCategory);
        tacos.getCategories().add(americanCategory);

        recipes.add(tacos);


        return recipes;
    }

}

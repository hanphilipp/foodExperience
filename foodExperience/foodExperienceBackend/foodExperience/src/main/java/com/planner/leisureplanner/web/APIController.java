package com.planner.leisureplanner.web;


import api.ApiResult;
import api.food.FoodRecipe;
import api.food.FoodResult;
import api.sportsCalc.SportsCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import api.NetClientGet;


@RestController
public class APIController {

    public static final String edamam_ID = "d22d84f9";
    public static final String edamam_KEY = "8eb18a2936415a1ad3f277d2f6632405";

    /*
    public static final String edamam_ID = "091eb186";
    public static final String edamam_KEY = "9929d29d003e513c870393b30a1e442d";
*/
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/recipes")
    public Object processFood(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "mass") double mass){

        FoodResult recipes = NetClientGet.requestRecipes("https://api.edamam.com/search?q=" + keyword + "&app_id=" + edamam_ID + "&app_key=" + edamam_KEY + "&from=0&to=10");

        for(int x = 0; x < recipes.hits.length; x++){
            if(recipes.hits[x].recipe.yield < 1) recipes.hits[x].recipe.yield = 1;
            recipes.hits[x].recipe.calories = Math.round(recipes.hits[x].recipe.calories / recipes.hits[x].recipe.yield);
            for (int i = 0; i<recipes.hits[x].recipe.digest.length; i++) {
                recipes.hits[x].recipe.digest[i].total = Math.round(recipes.hits[x].recipe.digest[i].total);
            }
    recipes.hits[x].recipe.activities = NetClientGet.requestActivities("http://localhost:25525/activity?mass=" + mass + "&kcal=" + (recipes.hits[x].recipe.calories));
}

    ApiResult apiResult = new ApiResult();
        apiResult.recipes = new FoodRecipe[recipes.hits.length];
                for (int x = 0; x < apiResult.recipes.length; x++) {
        apiResult.recipes[x] = recipes.hits[x].recipe;
        }

        try {
        return new ObjectMapper().writeValueAsString(apiResult);
        } catch (JsonProcessingException e) {
        return null;
        }

        }

    @CrossOrigin(origins = "http://localhost:25525")
    @RequestMapping("/activity")
    public Object calculateActivities(@RequestParam(value = "mass") double mass, @RequestParam(value = "kcal") double kcal){
        try{
            return new ObjectMapper().writeValueAsString(SportsCalculator.calculateSports(mass, kcal));
        }catch (Exception e) {
            return null;
        }
    }

}

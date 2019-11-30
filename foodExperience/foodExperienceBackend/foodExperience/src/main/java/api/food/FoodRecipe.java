package api.food;

import api.sportsCalc.Activity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"uri", "dietLabels", "healthLabels", "cautions", "ingredientLines", "totalNutrients", "totalDaily", "bookmarked", "bought"})
public class FoodRecipe {

    public String label;
    public String image;
    public String source;
    public String url;
    public String shareAs;
    public double calories;
    public double totalWeight;
    public double totalTime;
    public int yield;
    public Ingredients[] ingredients;
    public Nutrient[] digest;
    public ArrayList<Activity> activities;

}

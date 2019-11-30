package api.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"bookmarked", "bought"})
public class RecipeModel {

    public FoodRecipe recipe;

}

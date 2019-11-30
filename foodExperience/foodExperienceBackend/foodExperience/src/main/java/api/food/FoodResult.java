package api.food;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"q", "from", "to", "params", "more", "count"})
public class FoodResult {

    public RecipeModel[] hits;

}



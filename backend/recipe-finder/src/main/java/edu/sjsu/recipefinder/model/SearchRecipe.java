package edu.sjsu.recipefinder.model;

public class SearchRecipe {

    public String email;
    public String name;
    public String ingredients;
    public String estimatedTime;
    public String foodType;

    public SearchRecipe(String email, String name, String ingredients, String estimatedTime, String foodType) {
        this.email = email;
        this.name = name;
        this.ingredients = ingredients;
        this.estimatedTime = estimatedTime;
        this.foodType = foodType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}

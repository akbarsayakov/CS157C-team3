package edu.sjsu.recipefinder.model;

import java.util.Set;

public class PostRecipe {

    public String email;
    public String name;
    public String steps;
    public String ingredients;

    public String photo;

    public String estimatedTime;

    public String foodType;

    public String recipeNo;

    public PostRecipe(String email, String name, String ingredients, String steps, String photo, String estimatedTime, String foodType, String recipeNo) {
        this.email = email;
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.photo = photo;
        this.estimatedTime = estimatedTime;
        this.foodType = foodType;
        this.recipeNo = recipeNo;
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

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getRecipeNo() {
        return recipeNo;
    }

    public void setRecipeNo(String recipeNo) {
        this.recipeNo = recipeNo;
    }
}

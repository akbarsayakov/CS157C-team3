package edu.sjsu.recipefinder.model;

public class Recipe {
    public String name;
    public String[] steps;
    public String[] ingredients;

    public Recipe(String name, String[] steps, String[] ingredients) {
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
    }
}

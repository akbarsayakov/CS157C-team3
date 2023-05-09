package edu.sjsu.recipefinder.controller;

import edu.sjsu.recipefinder.model.*;
import edu.sjsu.recipefinder.service.DataLoader;
import edu.sjsu.recipefinder.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.List;

@RestController
@RequestMapping("/157C-team3/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final DataLoader dataLoader;
    public Jedis jedis;

    public RecipeController() {
        this.recipeService = new RecipeService();
        this.dataLoader = new DataLoader();
        this.jedis = new Jedis();
    }

    //load csv
    @PostMapping("/csv-recipe")
    public ResponseEntity<Message> loadCsvRecipe(){
        Message msg = dataLoader.loadData(jedis);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/csv-users")
    public ResponseEntity<Message> loadCsvUsers(){
        Message msg = dataLoader.loadUsers(jedis);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    //search recipes
    @GetMapping("/search")
    public ResponseEntity<List<PostRecipe>> search(SearchRecipe recipe) {
        List<PostRecipe> matchingRecipes = recipeService.searchRecipe(jedis, recipe);
        return new ResponseEntity<>(matchingRecipes, HttpStatus.OK);
    }

    //upload recipe
    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody PostRecipe recipe) throws Exception{
        Message msg = recipeService.createRecipe(jedis, recipe);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    //update recipe
    @PutMapping("/edit")
    public ResponseEntity<Message> edit(@RequestBody PostRecipe recipe) throws Exception{
        Message msg = recipeService.updateRecipe(jedis, recipe);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    //delete recipe
    @DeleteMapping("/delete")
    public ResponseEntity<Message> delete(@RequestBody DeleteRecipe recipe) throws Exception{
        Message msg = recipeService.deleteRecipe(jedis, recipe);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
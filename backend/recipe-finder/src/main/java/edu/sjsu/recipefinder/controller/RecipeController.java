package edu.sjsu.recipefinder.controller;

import edu.sjsu.recipefinder.model.Message;
import edu.sjsu.recipefinder.model.PostRecipe;
import edu.sjsu.recipefinder.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/recipe")
public class RecipeController {

    @GetMapping("/search")
    public ResponseEntity<PostRecipe[]> recipe(@RequestParam PostRecipe recipe) {
        // TODO query saved recipes in redis and load matches into memory
        return null;
    }


    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestParam PostRecipe recipe) {
        // TODO save this recipe in redis if not already present; handle errors
        return ResponseEntity.ok(new Message());
    }

    @PutMapping("/edit")
    public ResponseEntity<Message> edit(@RequestParam PostRecipe recipe) {
        // TODO edit this recipe in redis if not absent; handle errors
        return ResponseEntity.ok(new Message());
    }

    @PostMapping("/favorite")
    public ResponseEntity<Message> favorite(@RequestParam PostRecipe recipe) {
        // TODO mark this recipe as a favorite in redis if not absent; handle errors
        return ResponseEntity.ok(new Message());
    }
}
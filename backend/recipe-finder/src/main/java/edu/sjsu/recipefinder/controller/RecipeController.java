package edu.sjsu.recipefinder.controller;

import edu.sjsu.recipefinder.model.Message;
import edu.sjsu.recipefinder.model.Recipe;
import edu.sjsu.recipefinder.util.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/recipe")
public class RecipeController {

    @GetMapping("/search")
    public ResponseEntity<Recipe[]> recipe(@RequestParam Recipe recipe) {
        // TODO query saved recipes in redis and load matches into memory
        return null;
    }


    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestParam Recipe recipe) {
        // TODO save this recipe in redis if not already present; handle errors
        return ResponseEntity.ok(new Message(Constants.TEXT_WIP));
    }

    @PutMapping("/edit")
    public ResponseEntity<Message> edit(@RequestParam Recipe recipe) {
        // TODO edit this recipe in redis if not absent; handle errors
        return ResponseEntity.ok(new Message(Constants.TEXT_WIP));
    }

    @PostMapping("/favorite")
    public ResponseEntity<Message> favorite(@RequestParam Recipe recipe) {
        // TODO mark this recipe as a favorite in redis if not absent; handle errors
        return ResponseEntity.ok(new Message(Constants.TEXT_WIP));
    }
}
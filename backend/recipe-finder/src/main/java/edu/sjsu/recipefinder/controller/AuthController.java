package edu.sjsu.recipefinder.controller;

import edu.sjsu.recipefinder.model.Message;
import edu.sjsu.recipefinder.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody User.Credentials credentials) {
        // TODO save to redis if not present; handle errors
        return ResponseEntity.ok(new Message());
    }

    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody User.Credentials credentials) {
        // TODO match credentials with that in redis; handle errors
        return ResponseEntity.ok(new Message());
    }
}

package edu.sjsu.recipefinder.controller;

import edu.sjsu.recipefinder.model.*;
import edu.sjsu.recipefinder.service.LoginAndRegistrationService;
import edu.sjsu.recipefinder.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin("*")
@RequestMapping("/157C-team3/auth")
public class AuthController {

    private final LoginAndRegistrationService loginAndRegistrationService;
    public Jedis jedis;

    public AuthController() {
        this.loginAndRegistrationService = new LoginAndRegistrationService();
        this.jedis = new Jedis();
    }

    //register
    @PostMapping("/register")
    public ResponseEntity<Message> register(@RequestBody RegisterUser user) throws NoSuchAlgorithmException {
        Message msg = loginAndRegistrationService.register(jedis, user);
        if(msg.getText() == null){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }


    //login
    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginUser user) throws NoSuchAlgorithmException {
        Message msg = loginAndRegistrationService.login(jedis, user);
        if(!msg.getText().equals(Constants.WRONG_PASSWORD) && !msg.getText().equals(Constants.NO_SUCH_USER)){
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
        return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
    }
}
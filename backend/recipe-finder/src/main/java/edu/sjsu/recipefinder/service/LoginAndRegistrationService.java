package edu.sjsu.recipefinder.service;

import edu.sjsu.recipefinder.model.LoginUser;
import edu.sjsu.recipefinder.model.Message;
import edu.sjsu.recipefinder.model.RegisterUser;
import edu.sjsu.recipefinder.util.Constants;
import redis.clients.jedis.Jedis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LoginAndRegistrationService {
    public LoginAndRegistrationService() {
        //do nothing
    }



    public Message login(Jedis jedis, LoginUser creds) throws NoSuchAlgorithmException {
        Message msg = new Message();
        String password = jedis.hget("user_"+creds.email, "password");
        if(password == null){
            msg.setText(Constants.NO_SUCH_USER);
        } else if(!password.equals(hashPassword(creds.getPassword()))){
            msg.setText(Constants.WRONG_PASSWORD);
        } else{
            //password matches
            String username =jedis.hget("user_"+creds.email,"name");
            msg.setText(username);
        }
        return msg;
    }

    public Message register(Jedis jedis, RegisterUser user) throws NoSuchAlgorithmException {
        Message msg = new Message();

        boolean userExists = jedis.exists("user_"+user.email);

        if(userExists){
            msg.setText(Constants.USER_ALREADY_EXISTS);
        } else{
            //create new user hash
            Map<String,String> userHash = new HashMap<>();
            userHash.put("name",user.getName().trim());
            userHash.put("email",user.getEmail().trim());
            userHash.put("password",hashPassword(user.getPassword().trim()));
            System.out.println(jedis.hmset("user_"+user.getEmail(),userHash));
        }
        return msg;
    }

    public String hashPassword(String input) throws NoSuchAlgorithmException {
        // get an instance of the SHA-256 message digest algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // compute the hash of the input string
        byte[] hash = md.digest(input.getBytes());

        // convert the hash to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

}

package edu.sjsu.recipefinder.service;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import com.opencsv.CSVReader;
import edu.sjsu.recipefinder.model.Message;
import kong.unirest.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLoader {
    public DataLoader() {
        // do nothing
    }

    public String[] getExtractedIngredients(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer ");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        text = "list down only the food ingredients and not the quantities from this text: " + text;
        //text = "what is the time";
        data.put("prompt", text);
        data.put("max_tokens", 256);
        data.put("temperature", 0.7);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        String answer = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");

        if(answer.contains(",")) {
            answer.replaceAll(",","\\n");
        }
            return answer.split("\\n");
    }

    public Message loadData(Jedis jedis) {
        Message msg = new Message();
            try {

                FileReader filereader = new FileReader("/Users/anushree/Projects/CS157C-team3/dataset/recipe-dataset-final.csv");
                CSVReader csvReader = new CSVReader(filereader,';');
                String[] nextRecipe;
                int count = 1;

                nextRecipe = csvReader.readNext(); //skipping first line

                while ((nextRecipe = csvReader.readNext()) != null && count <= 14) {

                   int recipeNo = jedis.incr("recipe_count").intValue();
                   String recipeName = nextRecipe[0].trim();
                   String recipeSteps = nextRecipe[1].trim();
                   String recipePhoto = nextRecipe[2].trim();
                   String recipeEstimatedTime = nextRecipe[4].trim();
                   String recipeFoodType = nextRecipe[5].trim();
                   String recipeIngredientsQuantity = nextRecipe[3].trim();


                   jedis.set("recipe_name_"+recipeNo, recipeName);
                   jedis.set("recipe_steps_"+recipeNo, recipeSteps);
                   jedis.set("recipe_photo_"+recipeNo, recipePhoto);
                   jedis.set("recipe_estimatedtime_"+recipeNo, recipeEstimatedTime);
                   jedis.set("recipe_foodtype_"+recipeNo, recipeFoodType);
                   jedis.set("recipe_ingredients_quantity_"+recipeNo, recipeIngredientsQuantity);

                   String[] ingredients = getExtractedIngredients(nextRecipe[3].toLowerCase().trim());


                  for(String ingredient : ingredients){
                      if(!ingredient.isBlank())
                          jedis.sadd("recipe_ingredients_"+recipeNo, ingredient.replaceAll("-"," ").toLowerCase().trim());
                   }
                   count++;
                }
                msg.setText("Recipe Data inserted in Redis");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return msg;
        }

    public Message loadUser(Jedis jedis) {
        Message msg = new Message();
        try {

            FileReader filereader = new FileReader("/Users/anushree/Projects/CS157C-team3/dataset/users-dataset.csv");
            CSVReader csvReader = new CSVReader(filereader,',');
            String[] nextUser;
            int count = 1;

            nextUser = csvReader.readNext(); //skipping first line

            while ((nextUser = csvReader.readNext()) != null) {

                String email = nextUser[0].toLowerCase().trim();
                String name = nextUser[1].trim();
                String password = hashPassword(nextUser[2].trim());

                Map<String,String> map = new HashMap<>();
                map.put("email",email);
                map.put("name",name);
                map.put("password",password);

                jedis.hmset("user_"+email,map);

            }
            msg.setText("User Data inserted in Redis");
        }
        catch (Exception e) {
            e.printStackTrace();
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

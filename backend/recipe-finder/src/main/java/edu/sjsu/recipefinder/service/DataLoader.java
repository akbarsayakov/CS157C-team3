package edu.sjsu.recipefinder.service;

import com.lilittlecat.chatgpt.offical.ChatGPT;
import com.opencsv.CSVReader;
import kong.unirest.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DataLoader {
    public DataLoader() {
        // do nothing
    }

    public String[] getExtractedIngredients(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        text = "list only the food ingredients and not the quantities from this text: " + text;
        data.put("prompt", text);
        data.put("max_tokens", 256);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        String answer = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");

        if(answer.contains(",")) {
            answer.replaceAll(",","\\n");
        }
            return answer.split("\\n");
    }

    public void loadData(Jedis jedis) {
            try {

                FileReader filereader = new FileReader("/Users/anushree/Projects/CS157C-team3/dataset/recipe-dataset-small.csv");
                CSVReader csvReader = new CSVReader(filereader,';');
                String[] nextRecipe;
                int count = 1;

                nextRecipe = csvReader.readNext(); //skipping first line

                while ((nextRecipe = csvReader.readNext()) != null && count <= 10) {
                   System.out.println(nextRecipe.toString());
                   int recipeNo = jedis.incr("recipe_count").intValue();
                   String recipeName = nextRecipe[0].trim();
                   String recipeSteps = nextRecipe[1].trim();
                   String recipePhoto = nextRecipe[2].trim();
                   String recipeEstimatedTime = nextRecipe[4].trim();
                   String recipeFoodType = nextRecipe[5].trim();

                   jedis.set("recipe_name_"+recipeNo, recipeName);
                   jedis.set("recipe_steps_"+recipeNo, recipeSteps);
                   jedis.set("recipe_photo_"+recipeNo, recipePhoto);
                   jedis.set("recipe_estimatedtime_"+recipeNo, recipeEstimatedTime);
                   jedis.set("recipe_foodtype_"+recipeNo, recipeFoodType);

                   String[] ingredients = getExtractedIngredients(nextRecipe[3].toLowerCase().trim());
                   System.out.println("Recipe #" +count);

                  for(String ingredient : ingredients){
                      if(!ingredient.isBlank())
                          jedis.sadd("recipe_ingredients_"+recipeNo, ingredient.replaceAll("-"," ").toLowerCase().trim());
                   }

                   count++;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
}

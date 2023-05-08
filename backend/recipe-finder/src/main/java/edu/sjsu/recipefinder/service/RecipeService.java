package edu.sjsu.recipefinder.service;

import edu.sjsu.recipefinder.model.DeleteRecipe;
import edu.sjsu.recipefinder.model.Message;
import edu.sjsu.recipefinder.model.PostRecipe;
import edu.sjsu.recipefinder.model.SearchRecipe;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RecipeService {
    public RecipeService() {
        //do nothing
    }

    DataLoader loader = new DataLoader();
    public Message createRecipe(Jedis jedis, PostRecipe recipe) throws Exception {

        int recipeNo = jedis.incr("recipe_count").intValue();

        String recipeName = recipe.getName().trim();
        String recipeSteps = recipe.getSteps().trim();
        String recipePhoto = recipe.getPhoto().trim();
        String recipeEstimatedTime = recipe.getEstimatedTime().trim();
        String recipeFoodType = recipe.getFoodType().trim();
        String recipeIngredients = recipe.getIngredients().trim();

        jedis.set("recipe_name_"+recipeNo, recipeName);
        jedis.set("recipe_steps_"+recipeNo, recipeSteps);
        jedis.set("recipe_photo_"+recipeNo, recipePhoto);
        jedis.set("recipe_estimatedtime_"+recipeNo, recipeEstimatedTime);
        jedis.set("recipe_foodtype_"+recipeNo, recipeFoodType);
        jedis.set("recipe_ingredients_quantity_"+recipeNo, recipeIngredients);
        String[] ingredients = loader.getExtractedIngredients(recipe.getIngredients().toLowerCase().trim());


        for(String ingredient : ingredients){
            if(!ingredient.isBlank())
                jedis.sadd("recipe_ingredients_"+recipeNo, ingredient.replaceAll("-"," ").toLowerCase().trim());
        }

        jedis.rpush("recipe_user_"+recipe.getEmail(), String.valueOf(recipeNo));
        return new Message();
    }

    public Message updateRecipe(Jedis jedis, PostRecipe recipe) throws Exception {
        String recipeNumberToBeUpdated = "";
        List<String> recipes = jedis.lrange("recipe_user_"+recipe.getEmail(),0,-1);
        for(String i : recipes){
            String recipeName = jedis.get("recipe_name_"+i);
            if(recipeName.equals(recipe.getName())){
                recipeNumberToBeUpdated = i;
                break;
            }
        }

        if(recipe.getSteps() != null && !recipe.getSteps().trim().isEmpty()){
            jedis.set("recipe_steps_"+recipeNumberToBeUpdated, recipe.getSteps());
        }

        if(recipe.getPhoto() != null && !recipe.getPhoto().trim().isEmpty()){
            jedis.set("recipe_photo_"+recipeNumberToBeUpdated, recipe.getPhoto());
        }

        if(recipe.getEstimatedTime() != null && !recipe.getEstimatedTime().trim().isEmpty()){
            jedis.set("recipe_estimatedtime_"+recipeNumberToBeUpdated, recipe.getEstimatedTime());
        }

        if(recipe.getFoodType() != null && !recipe.getFoodType().trim().isEmpty()){
            jedis.set("recipe_foodtype_"+recipeNumberToBeUpdated, recipe.getFoodType());
        }

        if(recipe.getIngredients() != null && !recipe.getIngredients().trim().isEmpty()){
            jedis.set("recipe_ingredients_quantity_"+recipeNumberToBeUpdated, recipe.getIngredients().trim());
            String[] ingredients = loader.getExtractedIngredients(recipe.getIngredients().toLowerCase().trim());

            for(String ingredient : ingredients) {
                if (!ingredient.isBlank()) {
                    jedis.sadd("recipe_ingredients_" + recipeNumberToBeUpdated, ingredient.replaceAll("-", " ").toLowerCase().trim());
                }
            }
        }

        return new Message();
    }

    public Message deleteRecipe(Jedis jedis, DeleteRecipe recipe){
        String recipeNumberToBeDeleted = "";
        List<String> recipes = jedis.lrange("recipe_user_"+recipe.getEmail(),0,-1);
        for(String i : recipes){
            String recipeName = jedis.get("recipe_name_"+i);
            if(recipeName.equals(recipe.getName())){
                recipeNumberToBeDeleted= i;
                break;
            }
        }

        jedis.lrem("recipe_user_"+recipe.getEmail(),0,recipeNumberToBeDeleted);
        jedis.del("recipe_name_"+recipeNumberToBeDeleted);
        jedis.del("recipe_steps_"+recipeNumberToBeDeleted);
        jedis.del("recipe_photo_"+recipeNumberToBeDeleted);
        jedis.del("recipe_estimatedtime_"+recipeNumberToBeDeleted);
        jedis.del("recipe_foodtype_"+recipeNumberToBeDeleted);
        jedis.del("recipe_ingredients_"+recipeNumberToBeDeleted);
        jedis.del("recipe_ingredients_quantity_"+recipeNumberToBeDeleted);

        return new Message();
    }

    public List<PostRecipe> searchRecipe(Jedis jedis, SearchRecipe recipe){
        List<PostRecipe> listOfRecipes = new ArrayList<>();
        int count = Integer.parseInt(jedis.get("recipe_count"));

        if(recipe.getEmail() != null && !recipe.getEmail().trim().isEmpty()){
            List<String> myRecipes = jedis.lrange("recipe_user_"+recipe.getEmail(),0,-1);
            for(String i : myRecipes){
                String name = jedis.get("recipe_name_"+i);
                String steps = jedis.get("recipe_steps_"+i);
                String ingredients = jedis.get("recipe_ingredients_quantity_"+i);
                String photo = jedis.get("recipe_photo_"+i);
                String estimatedTime = jedis.get("recipe_estimatedtime_"+i);
                String foodType = jedis.get("recipe_foodtype_"+i);
                PostRecipe resultRecipe = new PostRecipe(recipe.getEmail(),name,ingredients,steps,photo,estimatedTime,foodType);
                listOfRecipes.add(resultRecipe);
            }
            return listOfRecipes;
        }


        if(recipe.getName() != null && !recipe.getName().trim().isEmpty()){
            String name = recipe.getName().toLowerCase().trim();


            if(name.split(" ").length == 1){
                for(int i = 1; i <= count; i++){
                    String dbRecipeName = jedis.get("recipe_name_"+i);
                    List<String> recipeNameWords = new ArrayList(Arrays.asList(dbRecipeName.split(" ")));
                    if(recipeNameWords.contains(name)){
                        String name1 = jedis.get("recipe_name_"+i);
                        String steps = jedis.get("recipe_steps_"+i);
                        String ingredients = jedis.get("recipe_ingredients_quantity_"+i);
                        String photo = jedis.get("recipe_photo_"+i);
                        String estimatedTime = jedis.get("recipe_estimatedtime_"+i);
                        String foodType = jedis.get("recipe_foodtype_"+i);
                        PostRecipe resultRecipe = new PostRecipe(recipe.getEmail(),name1,ingredients,steps,photo,estimatedTime,foodType);
                        listOfRecipes.add(resultRecipe);
                    }
                }
            } else{
                for(int i = 1; i <= count; i++){
                    String dbRecipeName = jedis.get("recipe_name_"+i).toLowerCase().trim();
                    if(dbRecipeName.equals(name)){
                        String name1 = jedis.get("recipe_name_"+i);
                        String steps = jedis.get("recipe_steps_"+i);
                        String ingredients = jedis.get("recipe_ingredients_quantity_"+i);
                        String photo = jedis.get("recipe_photo_"+i);
                        String estimatedTime = jedis.get("recipe_estimatedtime_"+i);
                        String foodType = jedis.get("recipe_foodtype_"+i);
                        PostRecipe resultRecipe = new PostRecipe(recipe.getEmail(),name1,ingredients,steps,photo,estimatedTime,foodType);
                        listOfRecipes.add(resultRecipe);
                    }
                }
            }
        }

        if(recipe.getEstimatedTime() != null && !recipe.getEstimatedTime().trim().isEmpty()){
            int desiredTime = Integer.parseInt(recipe.getEstimatedTime());
            if(listOfRecipes.size() > 0){
                List<PostRecipe> removeList = new ArrayList<>();
                for(PostRecipe shortlistedRecipe : listOfRecipes){
                    int requiredTime = Integer.parseInt(shortlistedRecipe.getEstimatedTime());
                    if(requiredTime > desiredTime){
                        removeList.add(shortlistedRecipe);
                    }
                }
                listOfRecipes.removeAll(removeList);
            } else{
                for(int i = 1; i <= count; i++){
                    int dbRecipeTime = Integer.parseInt(jedis.get("recipe_estimatedtime_"+i).trim());
                    if(desiredTime <= dbRecipeTime){
                        String name1 = jedis.get("recipe_name_"+i);
                        String steps = jedis.get("recipe_steps_"+i);
                        String ingredients = jedis.get("recipe_ingredients_quantity_"+i);
                        String photo = jedis.get("recipe_photo_"+i);
                        String estimatedTime = jedis.get("recipe_estimatedtime_"+i);
                        String foodType = jedis.get("recipe_foodtype_"+i);
                        PostRecipe resultRecipe = new PostRecipe(recipe.getEmail(),name1,ingredients,steps,photo,estimatedTime,foodType);
                        listOfRecipes.add(resultRecipe);
                    }
                }
            }
        }


        if(recipe.getFoodType() != null && !recipe.getFoodType().trim().isEmpty()){
            String desiredFoodType = recipe.getFoodType().toLowerCase().trim();
            if(listOfRecipes.size() > 0){
                List<PostRecipe> removeList = new ArrayList<>();
                for(PostRecipe shortlistedRecipe : listOfRecipes){
                    String foodType = shortlistedRecipe.getFoodType().toLowerCase().trim();
                    if(!desiredFoodType.equals(foodType)){
                        removeList.add(shortlistedRecipe);
                    }
                }
                listOfRecipes.removeAll(removeList);
            } else{
                for(int i = 1; i <= count; i++){
                    String dbFoodType = jedis.get("recipe_foodtype_"+i).toLowerCase().trim();
                    if(desiredFoodType.equals(dbFoodType)){
                        String name1 = jedis.get("recipe_name_"+i);
                        String steps = jedis.get("recipe_steps_"+i);
                        String ingredients = jedis.get("recipe_ingredients_quantity_"+i);
                        String photo = jedis.get("recipe_photo_"+i);
                        String estimatedTime = jedis.get("recipe_estimatedtime_"+i);
                        String foodType = jedis.get("recipe_foodtype_"+i);
                        PostRecipe resultRecipe = new PostRecipe(recipe.getEmail(),name1,ingredients,steps,photo,estimatedTime,foodType);
                        listOfRecipes.add(resultRecipe);
                    }
                }
            }
        }

        if(recipe.getIngredients() != null && !recipe.getIngredients().trim().isEmpty()){
            
        }



        return listOfRecipes;
    }
}

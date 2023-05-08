package edu.sjsu.recipefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class RecipeFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeFinderApplication.class, args);

        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server successfully");
        System.out.println("Server is running: "+jedis.ping());


        /*load csv data for recipes
        jedis.set("recipe_count","0");
        new DataLoader().loadData(jedis);
        */

        /* login module testing
        //correct creds
        LoginUser user1 = new LoginUser("anushreeshah24@gmail.com","pass@123");

        //user does not exist
        LoginUser user2 = new LoginUser("anushreeshah@gmail.com","pass@123");

        //wrong password
        LoginUser user3 = new LoginUser("anushreeshah24@gmail.com","pass@1234");

        Message msg1 = new LoginAndRegistrationService().login(jedis,user1);

        Message msg2 = new LoginAndRegistrationService().login(jedis,user2);

        Message msg3 = new LoginAndRegistrationService().login(jedis,user3);

        System.out.println(msg1.getText());
        System.out.println(msg2.getText());
        System.out.println(msg3.getText());
        */

        /*
        //existing user
        RegisterUser regUser1 = new RegisterUser("anushreeshah24@gmail.com","Anushree Shah","pass@123");

        //new user
        RegisterUser regUser2 = new RegisterUser("joey.tribbiani@gmail.com","Joey Tribbiani", "pass@123");

        Message regmsg1 = new LoginAndRegistrationService().register(jedis,regUser1);

        Message regmsg2 = new LoginAndRegistrationService().register(jedis,regUser2);

        System.out.println(regmsg1.getText());
        System.out.println(regmsg2.getText());


         */
        }

    }


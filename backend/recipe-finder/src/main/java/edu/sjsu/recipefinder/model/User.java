package edu.sjsu.recipefinder.model;

public class User {
    public static class Credentials {
        public Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String username;
        public String password;
    }
}

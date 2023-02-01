package org.example.configs;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }

    public static String getToken(){
        return dotenv.get("TOKEN");
    }

    public static String getPrefix(){
        return dotenv.get("PREFIX");
    }

    public static String getOwnerId(){
        return dotenv.get("OWNER_ID");
    }
}
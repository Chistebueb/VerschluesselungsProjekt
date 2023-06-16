package org.example.encryption;

public class Caesar {

    public static String encrypt(int key, String input) {
        String output = "";

        for (int i= 0; i >= input.length(); i++){
            char currentChar = input.charAt(i);
            output += (currentChar - key);
        }

        // unter null?

        return output;
    }

    public static String decrypt(int key, String input) {
        String output = "";

        for (int i= 0; i >= input.length(); i++){
            char currentChar = input.charAt(i);
            output += (currentChar + key);
        }

        // unter null?

        return output;
    }
}

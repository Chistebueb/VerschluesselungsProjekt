package org.example.encryption;

public class Vigenere implements Encryption {
    @Override
    public String encrypt(String sKey, String input) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = sKey.length();
        int inputLength = input.length();

        for (int i = 0; i < inputLength; i++) {
            char inputChar = input.charAt(i);
            char keyChar = sKey.charAt(i % keyLength);

            char encryptedChar = encryptCharacter(inputChar, keyChar);
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String sKey, String input) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = sKey.length();
        int inputLength = input.length();

        for (int i = 0; i < inputLength; i++) {
            char inputChar = input.charAt(i);
            char keyChar = sKey.charAt(i % keyLength);

            char decryptedChar = decryptCharacter(inputChar, keyChar);
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }

    private char encryptCharacter(char inputChar, char keyChar) {
        if (!Character.isLetter(inputChar)) {
            return inputChar; // Preserve non-alphabetic characters
        }

        int inputBase = Character.isUpperCase(inputChar) ? 'A' : 'a';
        int keyBase = Character.isUpperCase(keyChar) ? 'A' : 'a';

        int inputOffset = inputChar - inputBase;
        int keyOffset = keyChar - keyBase;

        int encryptedOffset = (inputOffset + keyOffset) % 26;
        char encryptedChar = (char) (inputBase + encryptedOffset);

        return encryptedChar;
    }

    private char decryptCharacter(char inputChar, char keyChar) {
        if (!Character.isLetter(inputChar)) {
            return inputChar; // Preserve non-alphabetic characters
        }

        int inputBase = Character.isUpperCase(inputChar) ? 'A' : 'a';
        int keyBase = Character.isUpperCase(keyChar) ? 'A' : 'a';

        int inputOffset = inputChar - inputBase;
        int keyOffset = keyChar - keyBase;

        int decryptedOffset = (inputOffset - keyOffset + 26) % 26;
        char decryptedChar = (char) (inputBase + decryptedOffset);

        return decryptedChar;
    }
}

package ch.bbw.verschluesselungm114.encryption;

public class Caesar implements Encryption{

    public String encrypt(String sKey, String input) {

        char firstChar = sKey.charAt(0);
        int key = firstChar;

        String output = "";

        for (int i= 0; i < input.length(); i++){
            char currentChar = input.charAt(i);
            int index = currentChar - key;
            output += ((char)index);
        }
        return output;
    }

    public String decrypt(String sKey, String input) {

        char firstChar = sKey.charAt(0);
        int key = firstChar;

        String output = "";

        for (int i= 0; i < input.length(); i++){
            char currentChar = input.charAt(i);
            int index = (currentChar + key);
            output += ((char)index);
        }

        return output;
    }

    public Caesar(){}
}

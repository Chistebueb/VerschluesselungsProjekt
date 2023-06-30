package ch.bbw.verschluesselungm114.encryption;

public class Xor implements Encryption {
    public String encrypt(String key, String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }

        return output.toString();
    }

    public String decrypt(String key, String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }

        return output.toString();
    }

    public Xor(){}
}

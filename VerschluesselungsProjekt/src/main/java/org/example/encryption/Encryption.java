package org.example.encryption;

public interface Encryption {
    public abstract String encrypt(String sKey, String input);
    public abstract String decrypt(String sKey, String input);
}

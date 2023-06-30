package ch.bbw.verschluesselungm114.encryption;
public interface Encryption {
    public abstract String encrypt(String key, String input);
    public abstract String decrypt(String key, String input);
}

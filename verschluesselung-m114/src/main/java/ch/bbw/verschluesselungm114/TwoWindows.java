package ch.bbw.verschluesselungm114;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;


public class TwoWindows {



    public static String key;
    static AppFX appfx = new AppFX();

    public static void main(String[] args) {

        //generiere key
        key = randomString();
        System.out.println(key);
        // Starte javafx start
        appfx.start();
    }

    //generiere zufällige zeichenkette
    public static String randomString() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}


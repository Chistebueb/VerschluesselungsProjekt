package ch.bbw.verschluesselungm114;

import ch.bbw.verschluesselungm114.encryption.Caesar;
import ch.bbw.verschluesselungm114.encryption.Encryption;
import ch.bbw.verschluesselungm114.encryption.PlayFair;
import ch.bbw.verschluesselungm114.encryption.Xor;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;


public class SingleWindow {

    public static String key;


    static AppFX2 appfx2 = new AppFX2();

    public static void main(String[] args) {

        //generiere key
        key = randomString();
        System.out.println(key);
        // Starte javafx start
        appfx2.start();
    }


    //generiere zufällige zeichenkette
    public static String randomString() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}

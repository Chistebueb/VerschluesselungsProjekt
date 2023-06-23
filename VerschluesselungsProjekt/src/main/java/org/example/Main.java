package org.example;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.example.encryption.Caesar;
import org.example.encryption.Encryption;
import org.example.encryption.Xor;

import java.io.File;

import java.net.URL;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {

    private TextField textField1;
    private TextField textField2;
    private Stage stage1;
    private Stage stage2;
    private Label label1;
    private Label label2;
    private static String key;

    private ArrayList<Button> buttons = new ArrayList<Button>();

    public Encryption encryptionType = new Xor();

    public static void main(String[] args) {

        //generiere key
        key = randomString();
        System.out.println(key);
        

        // Starte javafx start
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // 1. Fenster erstelle
        stage1 = new Stage();
        stage1.setTitle("Window 1");
        createWindow(stage1);

        // 2. Fenster erstelle
        stage2 = new Stage();
        stage2.setTitle("Window 2");
        createWindow(stage2);

        // Position liecht versetze, damit die beiden Fenster nöd genau übereinander sind.
        positionWindows();

        // Im show alli Elemente erstelle voh beide Fenster
        stage1.show();
        stage2.show();

        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(encryptionType.getClass() == (new Xor()).getClass()) {
                    buttons.get(0).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(3).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(4).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(5).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                }
                else if(encryptionType.getClass() == (new Caesar()).getClass()) {

                    buttons.get(0).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(3).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(4).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(5).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

                }
                else {

                    buttons.get(0).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(3).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(4).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(5).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");

                }
            }
        };

        animator.start();
    }

    // Fenster versetze
    private void positionWindows() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double x = stage1.getX() + stage1.getWidth() + 20;
        double y = screenBounds.getMinY() + (screenBounds.getHeight() - stage1.getHeight()) / 2;

        stage1.setX(screenBounds.getMinX());
        stage1.setY(y);
        stage2.setX(x);
        stage2.setY(y);
    }

    public void createWindow(Stage stage) {
        Label title = new Label("Encryption");
        Label instruction = new Label("pick type:");

        Button xorButton = new Button(" Xor  ");
        buttons.add(xorButton);
        Button caesarButton = new Button("Caesar");
        buttons.add(caesarButton);
        Button unsignedButton = new Button(" more ");
        buttons.add(unsignedButton);
        TextField textField = new TextField();
        Button button = new Button(" Send  ");
        Button button2 = new Button("Decrypt");
        Label displayLabel = new Label();

        // Verschlüssle und sende d'ihgebeni Nachricht, wenn send button drückt wird
        button.setOnAction(e -> {
            String inputText = textField.getText();
            System.out.println("Input: " + inputText);

            if (stage == stage1) {
                //Falls de Button ih Stage 1 drückt wird, setze die nachricht in das Label voh Stage 2 ih
                label2.setText(encryptionType.encrypt(key, inputText));
                textField1.clear();
            } else if (stage == stage2) {
                // S'Gliiche, aber anderst rum
                label1.setText(encryptionType.encrypt(key, inputText));
                textField2.clear();
            }
        });

        // Entschlüssle erhalteni Nachricht, wenn decrypt button drückt wird
        button2.setOnAction(e -> {
            if (stage == stage1) {
                //Falls de Button ih Stage 1 drückt wird, setze die nachricht in das Label voh Stage 2 ih
                label1.setText(encryptionType.decrypt(key, label1.getText()));

            } else if (stage == stage2) {
                // S'Gliiche, aber anderst rum
                label2.setText(encryptionType.decrypt(key, label2.getText()));
            }
        });

        VBox root = new VBox(10);

        //scene erstelle
        Scene scene = new Scene(root, 500, 220);
        scene.setFill(Color.web("#151A30"));

        stage.setScene(scene);


        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.web("#151A30"), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(button, button2);

        HBox typeButtonBox = new HBox(5);

        xorButton.setPrefWidth((scene.getWidth()/3)-5);
        caesarButton.setPrefWidth((scene.getWidth()/3)-5);
        unsignedButton.setPrefWidth((scene.getWidth()/3)-5);
        typeButtonBox.getChildren().addAll(xorButton, caesarButton, unsignedButton);



        root.getChildren().addAll(title, textField, buttonBox, displayLabel, instruction, typeButtonBox);



        //style
        title.setStyle(" -fx-text-fill: #FFFFFF;");
        instruction.setStyle(" -fx-text-fill: #FFFFFF;");
        textField.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #1f253f; -fx-prompt-text-fill: #FFFFFF;");
        button.setStyle("-fx-background-color: #17bebb; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
        button2.setStyle("-fx-background-color: #17bebb; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
        displayLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        title.setFont(Font.loadFont("fonts/Oneday/ONEDAY.ttf", 20));
        instruction.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));




        // Wächsle verschlüsselig zu Xor
        xorButton.setOnAction(e -> {
            encryptionType = new Xor();
            label1.setText("");
            label2.setText("");
        });

        // Wächsle verschlüsselig zu Caesar
        caesarButton.setOnAction(e -> {
            encryptionType = new Caesar();
            label1.setText("");
            label2.setText("");
        });


        // Wächsle verschlüsselig zu ...
        caesarButton.setOnAction(e -> {
            encryptionType = new Caesar();
            label1.setText("");
            label2.setText("");
        });


        //alles update
        if (stage == stage1) {
            stage1 = stage;
            textField1 = textField;
            label1 = displayLabel;
        } else if (stage == stage2) {
            stage2 = stage;
            textField2 = textField;
            label2 = displayLabel;
        }

        //falls eis Fenster gschlosse wird, söds andere au zuegah
        stage.setOnCloseRequest(event -> {
            stage1.close();
            stage2.close();
        });
    }

    //generiere zufällige zeichenkette
    public static String randomString() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}

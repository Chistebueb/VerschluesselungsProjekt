package ch.bbw.verschluesselungm114;

import ch.bbw.verschluesselungm114.encryption.Caesar;
import ch.bbw.verschluesselungm114.encryption.Encryption;
import ch.bbw.verschluesselungm114.encryption.PlayFair;
import ch.bbw.verschluesselungm114.encryption.Xor;
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


public class SingleWindow extends Application {

    private TextField textField1;
    private Stage stage;
    private Label label;

    private static Button decryptButton1;
    private static String key;

    

    private ArrayList<Button> buttons = new ArrayList<Button>();

    public Encryption encryptionType = new Xor();

    final static Font ONEDAY = Font.loadFont("file:fonts/Oneday/ONEDAY.ttf", 20);
    final static Font kulimParkBig = Font.loadFont("file:fonts/KulimPark/KulimPark-Light.ttf", 25);
    final static Font kulimParkSmall = Font.loadFont("file:fonts/KulimPark/KulimPark-Regular.ttf", 13);
    final static Font kulimParkBold = Font.loadFont("file:fonts/KulimPark/KulimPark-Bold.ttf", 13);


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
        stage = new Stage();
        stage.setTitle("Encryption");
        createWindow(stage);

        // Im show alli Elemente erstelle voh beide Fenster
        stage.show();

        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(encryptionType.getClass() == (new Xor()).getClass()) {
                    buttons.get(0).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                }
                else if(encryptionType.getClass() == (new Caesar()).getClass()) {

                    buttons.get(0).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                }
                else {

                    buttons.get(0).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(1).setStyle("-fx-background-color: #1f253f; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
                    buttons.get(2).setStyle("-fx-background-color: #00cc99; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
                }
            }
        };

        animator.start();
    }

    public void createWindow(Stage stage) {
        Label title = new Label("Encryption");
        Label instruction = new Label("pick type:");

        Button xorButton = new Button(" Xor  ");
        buttons.add(xorButton);
        Button caesarButton = new Button("Caesar");
        buttons.add(caesarButton);
        Button playFairButton = new Button("PlayFair");
        buttons.add(playFairButton);
        TextField textField = new TextField();
        Button button = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");

        Label displayLabel = new Label();

        // Verschlüssle und sende d'ihgebeni Nachricht, wenn send button drückt wird
        button.setOnAction(e -> {
            String inputText = textField.getText();
            if(!inputText.equals("")) {
                System.out.println("Input: " + inputText);

                label.setText(encryptionType.encrypt(key, inputText));
                textField1.clear();
                decryptButton1.setDisable(false);
            }
        });

        decryptButton.setDisable(true);

        // Entschlüssle erhalteni Nachricht, wenn decrypt button drückt wird
        decryptButton.setOnAction(e -> {
            label.setText(encryptionType.decrypt(key, label.getText()));
            decryptButton1.setDisable(true);
        });

        VBox root = new VBox(10);

        //scene erstelle
        Scene scene = new Scene(root, 500, 220);
        scene.setFill(Color.web("#151A30"));

        stage.setScene(scene);


        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.web("#151A30"), CornerRadii.EMPTY, Insets.EMPTY)));

        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(button, decryptButton);

        HBox typeButtonBox = new HBox(5);

        xorButton.setPrefWidth((scene.getWidth()/3)-5);
        caesarButton.setPrefWidth((scene.getWidth()/3)-5);
        playFairButton.setPrefWidth((scene.getWidth()/3)-5);
        typeButtonBox.getChildren().addAll(xorButton, caesarButton, playFairButton);



        root.getChildren().addAll(title, textField, buttonBox, displayLabel, instruction, typeButtonBox);



        //style
        title.setStyle(" -fx-text-fill: #FFFFFF;");
        instruction.setStyle(" -fx-text-fill: #FFFFFF;");
        textField.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: #1f253f; -fx-prompt-text-fill: #FFFFFF;");
        button.setStyle("-fx-background-color: #17bebb; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
        decryptButton.setStyle("-fx-background-color: #17bebb; -fx-text-fill: #1f253f; -fx-font-weight: bold;");
        displayLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        button.setPrefWidth(72);
        decryptButton.setPrefWidth(72);



        title.setFont(kulimParkBig);
        instruction.setFont(kulimParkSmall);




        // Wächsle verschlüsselig zu Xor
        xorButton.setOnAction(e -> {
            encryptionType = new Xor();
            label.setText("");
            decryptButton1.setDisable(true);
        });

        // Wächsle verschlüsselig zu Caesar
        caesarButton.setOnAction(e -> {
            encryptionType = new Caesar();
            label.setText("");
            decryptButton1.setDisable(true);
        });


        // Wächsle verschlüsselig zu ...
        playFairButton.setOnAction(e -> {
            encryptionType = new PlayFair();
            label.setText("");
            decryptButton1.setDisable(true);
        });


        //alles update
        this.stage = stage;
        textField1 = textField;
        label = displayLabel;

        //teile de gstylti decrypt button zu de dementsprechende static variable zue
        decryptButton1 = decryptButton;
    }

    //generiere zufällige zeichenkette
    public static String randomString() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        return generatedString;
    }
}

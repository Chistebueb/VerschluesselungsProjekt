package ch.bbw.verschluesselungm114;


import ch.bbw.verschluesselungm114.encryption.Caesar;
import ch.bbw.verschluesselungm114.encryption.Encryption;
import ch.bbw.verschluesselungm114.encryption.PlayFair;
import ch.bbw.verschluesselungm114.encryption.Xor;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;


public class AppFX extends Application {

    public void start() {
        launch();
    }
    private TextField textField1;
    private TextField textField2;
    private Stage stage1;
    private Stage stage2;
    private Label label1;
    private Label label2;

    private static Button decryptButton1;
    private static Button decryptButton2;


    private final ArrayList<Button> buttons = new ArrayList<Button>();

    public Encryption encryptionType = new Xor();

    final static Font kulimParkBig = Font.loadFont("file:fonts/KulimPark/KulimPark-Light.ttf", 25);
    final static Font kulimParkSmall = Font.loadFont("file:fonts/KulimPark/KulimPark-Regular.ttf", 13);

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

        stage1.getIcons().add(new Image("file:img/Icon.png"));
        stage2.getIcons().add(new Image("file:img/Icon.png"));

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
        Label instruction = new Label("Pick type:");
        instruction.setPadding(new Insets(24, 0, 0, 0));

        Button xorButton = new Button(" Xor  ");
        buttons.add(xorButton);
        Button caesarButton = new Button("Caesar");
        buttons.add(caesarButton);
        Button playFairButton = new Button("PlayFair");
        buttons.add(playFairButton);
        TextField textField = new TextField();
        Button button = new Button("Send");
        Button decryptButton = new Button("Decrypt");

        Label displayLabel = new Label();

        // Verschlüssle und sende d'ihgebeni Nachricht, wenn send button drückt wird
        button.setOnAction(e -> {
            String inputText = textField.getText();
            if(!inputText.equals("")) {
                System.out.println("Input: " + inputText);

                if (stage == stage1) {
                    //Falls de Button ih Stage 1 drückt wird, setze die nachricht in das Label voh Stage 2 ih
                    label2.setText(encryptionType.encrypt(TwoWindows.key, inputText));
                    textField1.clear();
                    decryptButton2.setDisable(false);
                } else if (stage == stage2) {
                    // S'Gliiche, aber anderst rum
                    label1.setText(encryptionType.encrypt(TwoWindows.key, inputText));
                    textField2.clear();
                    decryptButton1.setDisable(false);
                }
            }
        });

        decryptButton.setDisable(true);

        // Entschlüssle erhalteni Nachricht, wenn decrypt button drückt wird
        decryptButton.setOnAction(e -> {
            if (stage == stage1) {
                //Falls de Button ih Stage 1 drückt wird, setze die nachricht in das Label voh Stage 2 ih
                label1.setText(encryptionType.decrypt(TwoWindows.key, label1.getText()));
                decryptButton1.setDisable(true);

            } else if (stage == stage2) {
                // S'Gliiche, aber anderst rum
                label2.setText(encryptionType.decrypt(TwoWindows.key, label2.getText()));
                decryptButton2.setDisable(true);
            }
        });

        VBox root = new VBox(10);

        //scene erstelle
        Scene scene = new Scene(root, 500, 250);
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
            label1.setText("");
            label2.setText("");
            decryptButton1.setDisable(true);
            decryptButton2.setDisable(true);
        });

        // Wächsle verschlüsselig zu Caesar
        caesarButton.setOnAction(e -> {
            encryptionType = new Caesar();
            label1.setText("");
            label2.setText("");
            decryptButton1.setDisable(true);
            decryptButton2.setDisable(true);
        });


        // Wächsle verschlüsselig zu ...
        playFairButton.setOnAction(e -> {
            encryptionType = new PlayFair();
            label1.setText("");
            label2.setText("");
            decryptButton1.setDisable(true);
            decryptButton2.setDisable(true);
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

        //teile de gstylti decrypt button zu de dementsprechende static variable zue
        if(stage == stage1) {
            decryptButton1 = decryptButton;
        }
        else {
            decryptButton2 = decryptButton;
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


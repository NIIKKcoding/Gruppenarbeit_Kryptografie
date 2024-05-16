package OneTimePad;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OTPView {
    public Stage stage;
    private OTPModel model;
    public TextField originalMessage = new TextField();
    public Button encryptButton = new Button("Encrypt");
    public Button decryptButton = new Button("Decrypt");
    public Button resetButton = new Button("Reset");
    public Button loadPadButton = new Button("Load Pad");
    public TextField result = new TextField();

    public OTPView(Stage stage, OTPModel model) {
        this.stage = stage;
        this.model = model;
        createView();
    }

    private void createView() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10)); // Setzt den Innenabstand für das Layout


        HBox loadPadBox = new HBox(loadPadButton);
        loadPadBox.setAlignment(Pos.CENTER);


        VBox inputArea = new VBox(5);
        inputArea.setAlignment(Pos.CENTER); // Zentriert die Inhalte im VBox
        inputArea.getChildren().addAll(new Label("Enter your message:"), originalMessage);


        HBox buttonBar = new HBox(10, encryptButton, decryptButton);
        buttonBar.setAlignment(Pos.CENTER);


        VBox outputArea = new VBox(5);
        outputArea.setAlignment(Pos.CENTER);
        outputArea.getChildren().addAll(new Label("Your new message:"), result);


        HBox resetBox = new HBox(resetButton);
        resetBox.setAlignment(Pos.CENTER);


        root.getChildren().addAll(loadPadBox, inputArea, buttonBar, outputArea, resetBox);

        Scene scene = new Scene(root, 600, 400); // Setzt die Größe des Fensters
        stage.setTitle("One-Time Pad Cryptography"); // Titel des Fensters
        scene.getStylesheets().add(getClass().getResource("OTP.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public void start() {
    }
}

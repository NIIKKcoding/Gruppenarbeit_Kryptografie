package OneTimePad;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class OTPController {
    private OTPModel model;
    private OTPView view;
    // Konstruktor, der das Modell und die Ansicht initialisiert und Ereignishandler einrichtet
    public OTPController(OTPView view, OTPModel model) {
        this.view = view;
        this.model = model;
        setupHandlers();
    }

    private void setupHandlers() {
        view.encryptButton.setOnAction(this::encrypt);
        view.decryptButton.setOnAction(this::decrypt);
        view.resetButton.setOnAction(e -> reset());
        view.loadPadButton.setOnAction(this::loadPad);
    }

    private void encrypt(ActionEvent e) {
        try {
            String encrypted = model.encryptMessage(view.originalMessage.getText());
            view.result.setText(encrypted); // Setzt das verschlüsselte Ergebnis ins Ergebnisfeld
        } catch (IllegalArgumentException ex) {
            view.result.setText("Error: " + ex.getMessage()); // Zeigt Fehlermeldungen bei Problemen an
        }
    }

    private void decrypt(ActionEvent e) {
        try {
            String decrypted = model.decryptMessage(view.originalMessage.getText());
            view.result.setText(decrypted); // Setzt das entschlüsselte Ergebnis ins Ergebnisfeld
        } catch (IllegalArgumentException ex) {
            view.result.setText("Error: " + ex.getMessage()); // Zeigt Fehlermeldungen bei Problemen an
        }
    }

    private void loadPad(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(view.stage);
        if (file != null) {
            try {
                String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
                model.setPad(content.replaceAll("[^\\x20-\\x7E]", ""));
                view.result.setText("Pad loaded successfully."); // Bestätigung, dass das Pad geladen wurde
            } catch (Exception ex) {
                view.result.setText("Error loading file: " + ex.getMessage()); // Fehlermeldung, falls das Laden scheitert
            }
        }
    }
    // Setzt die Textfelder der Anwendung zurück
    private void reset() {
        view.originalMessage.clear();
        view.result.clear(); // reset Methode mithilfe von Code Guru erstellt
    }
}


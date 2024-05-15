package OneTimePad;

import javafx.application.Application;
import javafx.stage.Stage;

public class OTPMain extends Application {
    private OTPModel model;
    private OTPView view;
    private OTPController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        model = new OTPModel();
        view = new OTPView(primaryStage, model);
        controller = new OTPController(view, model);

        view.start();
    }
}
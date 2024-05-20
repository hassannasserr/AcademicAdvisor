package app.LoadingPage2;

import app.Administrator.AdminApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.util.Random;

public class LoadingPageController {
    @FXML
    private ProgressBar PrBar= new ProgressBar();
    @FXML
    private Label load= new Label();
    @FXML
    private Label msg= new Label();

    private String[] loadingMessages = {
            "Registration process is easier with us...",
            "make sure you are in the right place...",
            " always think about success...",
    };

    @FXML
    private void initialize() {
        PrBar.setProgress(0.1);
        new Thread(() -> {
            try {
                Random random = new Random();
                while (PrBar.getProgress() < 1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double newProgress = PrBar.getProgress() + 0.1;
                    int randomMessageIndex = random.nextInt(loadingMessages.length);
                    Platform.runLater(() -> {
                        PrBar.setProgress(newProgress);
                        msg.setText(loadingMessages[randomMessageIndex]);
                    });
                }
                if(PrBar.getProgress() > 1){
                    Platform.runLater(() -> {
                        Stage currentStage = (Stage) PrBar.getScene().getWindow();
                        currentStage.close();
                        AdminApplication adminApplication = new AdminApplication();
                        try {
                            Stage newStage = new Stage();
                            adminApplication.start(newStage);
                            newStage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
        String load1 = "Loading...";
        new Thread(() -> {
            try {
                for (int i = 0; i < load1.length(); i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String finalLoad = load1.substring(0, i + 1);
                    Platform.runLater(() -> load.setText(finalLoad));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}

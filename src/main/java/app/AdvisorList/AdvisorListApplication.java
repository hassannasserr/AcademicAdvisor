package app.AdvisorList;

import app.Administrator.AdminApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdvisorListApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdvisorListApplication.class.getResource("AdvisorList.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Page!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

package app.Administrator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("AdministratorDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home Page!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

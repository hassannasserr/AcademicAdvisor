package app.LoadingPage2;

import app.LoadingPage.LoadingApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingPageApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoadingPageApplication.class.getResource("LoadingPage2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Loading Page!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

package app.RegistrationProcess;

import app.CoursesSearch.CoursesSearchApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationProcessApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
@Override
    public void start(Stage pstage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(RegistrationProcessApplication.class.getResource("/app/RegisterationProccess/RegistrationProcess.fxml"));       Scene scene = new Scene(fxmlLoader.load());
        pstage.setTitle("Hello!");
        pstage.setScene(scene);
        pstage.setResizable(false);
        pstage.show();
    }
}

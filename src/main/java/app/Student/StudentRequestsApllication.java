package app.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentRequestsApllication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentRequestsApllication.class.getResource("StudentRequests.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student Asking Questions!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

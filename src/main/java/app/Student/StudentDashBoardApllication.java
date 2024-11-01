package app.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class StudentDashBoardApllication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StudentDashBoardApllication.class.getResource("StudenDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student DashBoard!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

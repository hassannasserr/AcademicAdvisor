package app.Student.StudentSearch;

import app.Student.StudentDashBoardApllication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StudentSearchApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        File fxmlFile = new File("C:\\Users\\Lenovo\\Documents\\GitHub\\AcademicAdvisor\\src\\main\\resources\\app\\Student\\StudentSearch\\StudentSearch.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student Search Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

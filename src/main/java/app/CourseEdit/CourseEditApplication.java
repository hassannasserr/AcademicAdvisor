package app.CourseEdit;

import app.CoursesSearch.CoursesSearchApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseEditApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoursesSearchApplication.class.getResource("/app/CourseEdit/CourseEdit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Course Edit Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

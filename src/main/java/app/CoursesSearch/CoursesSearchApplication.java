package app.CoursesSearch;

import app.Dashboard.DashBoardApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoursesSearchApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage pstage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoursesSearchApplication.class.getResource("/app/CoursesSearch/CoursesSearch.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        pstage.setTitle("Course Search Page");
        pstage.setScene(scene);
        pstage.setResizable(false);
        pstage.show();
    }
}

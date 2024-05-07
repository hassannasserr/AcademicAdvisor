package app.AnswerRequests;

import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AnswerRequestsApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CoursesSearchApplication.class.getResource("/app/AnswerRequests/AnswerRequests.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Answering Requests Page");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

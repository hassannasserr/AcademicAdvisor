package app.Student;
import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.Student.StudentSearch.StudentSearchApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class StudentDashBoardController {
    @FXML
    private Button HomePage;

    @FXML
    private Button CourseSea;
    @FXML
    private Button AnswerReq;

    @FXML
    void initialize() {
        DashBoard();
    }
        private void DashBoard() {
            HomePage.setOnAction((event) -> {
                try {
                    Stage currentStage = (Stage) HomePage.getScene().getWindow();
                    currentStage.close();
                    StudentDashBoardApllication studentDashBoardApllication = new StudentDashBoardApllication();
                    Stage newStage = new Stage();
                    studentDashBoardApllication.start(newStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            CourseSea.setOnAction((event) -> {
                try {
                    Stage currentStage = (Stage) HomePage.getScene().getWindow();
                    currentStage.close();
                    StudentSearchApplication coursesSearchApplication = new StudentSearchApplication();
                    Stage newStage = new Stage();
                    coursesSearchApplication.start(newStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            AnswerReq.setOnAction((event) -> {
                try {
                    Stage currentStage = (Stage) HomePage.getScene().getWindow();
                    currentStage.close();
                   StudentRequestsApllication studentRequestsApllication = new StudentRequestsApllication();
                    Stage newStage = new Stage();
                    studentRequestsApllication.start(newStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            HomePage.setOnMouseEntered((event) -> {
              HomePage.setOpacity(0.5);
              HomePage.setScaleX(1.1);
              HomePage.setScaleY(1.1);
            });
            HomePage.setOnMouseExited((event) -> {
              HomePage.setOpacity(1);
              HomePage.setScaleX(1);
              HomePage.setScaleY(1);
            });
            CourseSea.setOnMouseEntered((event) -> {
              CourseSea.setOpacity(0.5);
              CourseSea.setScaleX(1.1);
              CourseSea.setScaleY(1.1);
            });
            CourseSea.setOnMouseExited((event) -> {
              CourseSea.setOpacity(1);
              CourseSea.setScaleX(1);
              CourseSea.setScaleY(1);
            });
            AnswerReq.setOnMouseEntered((event) -> {
              AnswerReq.setOpacity(0.5);
              AnswerReq.setScaleX(1.1);
              AnswerReq.setScaleY(1.1);
            });
            AnswerReq.setOnMouseExited((event) -> {
              AnswerReq.setOpacity(1);
              AnswerReq.setScaleX(1);
              AnswerReq.setScaleY(1);
            });

        }

}


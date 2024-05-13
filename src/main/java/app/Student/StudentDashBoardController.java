package app.Student;
import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
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
    private Label HomePage;
    @FXML
    private Label StudentReg;
    @FXML
    private Label CourseSea ;
    @FXML
    private Label EditCor;
    @FXML
    private Label AnswerReq;
    @FXML
    private Label LogOut;
    @FXML
    void initialize() {
        DashBoard();
    }
        private void DashBoard() {
            HomePage.setOnMouseClicked((event) -> {
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
            CourseSea.setOnMouseClicked((event) -> {
                try {
                    Stage currentStage = (Stage) HomePage.getScene().getWindow();
                    currentStage.close();
                    CoursesSearchApplication coursesSearchApplication = new CoursesSearchApplication();
                    Stage newStage = new Stage();
                    coursesSearchApplication.start(newStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            AnswerReq.setOnMouseClicked((event) -> {
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
            LogOut.setOnMouseClicked((event) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Logout");
                alert.setHeaderText("Are you sure you want to log out?");
                alert.setContentText("Choose your option.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    try {
                        Stage currentStage = (Stage) LogOut.getScene().getWindow();
                        currentStage.close();
                        LoginApplication loginApplication = new LoginApplication();
                        Stage newStage = new Stage();
                        loginApplication.start(newStage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

}


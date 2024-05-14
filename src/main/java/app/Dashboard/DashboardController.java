package app.Dashboard;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CourseEdit.CourseEditController;
import app.CourseSearch.CourseSearchApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class DashboardController {
    @FXML
    private Button HomePage;
    @FXML
    private Button StudentReg;
    @FXML
    private Button CourseSea ;
    @FXML
    private Button EditCor;
    @FXML
    private Button AnswerReq;
    @FXML
    private Button LogOut;
    @FXML
    private Label Date;
    @FXML
    private void initialize(){
        Date.setText("Date: " + java.time.LocalDate.now());
        HomePage.setOnAction((event) -> {
            try {
                // Reset the text fill of all labels to black
                HomePage.setTextFill(Color.BLACK);
                StudentReg.setTextFill(Color.BLACK);
                CourseSea.setTextFill(Color.BLACK);
                EditCor.setTextFill(Color.BLACK);
                AnswerReq.setTextFill(Color.BLACK);
                LogOut.setTextFill(Color.BLACK);

                // Change the text fill of the HomePage label to light blue
                HomePage.setTextFill(Color.web("#ADD8E6"));

                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                DashBoardApplication dashBoardApplication = new DashBoardApplication();
                Stage newStage = new Stage();
                dashBoardApplication.start(newStage);
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
        StudentReg.setOnMouseEntered((event) -> {
            StudentReg.setOpacity(0.5);
            StudentReg.setScaleX(1.1);
            StudentReg.setScaleY(1.1);
        });
        StudentReg.setOnMouseExited((event) -> {
            StudentReg.setOpacity(1);
            StudentReg.setScaleX(1);
            StudentReg.setScaleY(1);
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
        EditCor.setOnMouseEntered((event) -> {
            EditCor.setOpacity(0.5);
            EditCor.setScaleX(1.1);
            EditCor.setScaleY(1.1);
        });
        EditCor.setOnMouseExited((event) -> {
            EditCor.setOpacity(1);
            EditCor.setScaleX(1);
            EditCor.setScaleY(1);
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
        LogOut.setOnMouseEntered((event) -> {
            LogOut.setOpacity(0.5);
            LogOut.setScaleX(1.1);
            LogOut.setScaleY(1.1);
        });
        LogOut.setOnMouseExited((event) -> {
            LogOut.setOpacity(1);
            LogOut.setScaleX(1);
            LogOut.setScaleY(1);
        });
        StudentReg.setOnAction((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                StRegApplication stRegApplication = new StRegApplication();
                Stage newStage = new Stage();
                stRegApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        CourseSea.setOnAction((event) -> {
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
        EditCor.setOnAction((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                CourseEditApplication courseEditApplication = new CourseEditApplication();
                Stage newStage = new Stage();
                courseEditApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        AnswerReq.setOnAction((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                AnswerRequestsApplication answerRequestsApplication = new AnswerRequestsApplication();
                Stage newStage = new Stage();
                answerRequestsApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        LogOut.setOnAction((event) -> {
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

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class DashboardController {
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
    private void initialize(){
      HomePage.setOnMouseClicked((event) -> {
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



        StudentReg.setOnMouseClicked((event) -> {
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
        CourseSea.setOnMouseClicked((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                CourseSearchApplication coursesSearchApplication = new CourseSearchApplication();
                Stage newStage = new Stage();
                coursesSearchApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        EditCor.setOnMouseClicked((event) -> {
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
        AnswerReq.setOnMouseClicked((event) -> {
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

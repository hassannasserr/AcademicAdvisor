package app.Administrator;

import app.AddAdvisor.AddAdvisorApplication;
import app.AdvisorList.AdvisorListApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminController {
    @FXML
    private Button  AddAdvisor;
    @FXML
    private Button Advisor;
    @FXML
    private Button AdvisorList;
    @FXML
    private Button LogOut;
    @FXML
    void initialize(){
        AddAdvisor.setOnMouseEntered((event)->{
            AddAdvisor.setOpacity(0.5);
            AddAdvisor.setScaleX(1.1);
            AddAdvisor.setScaleY(1.1);
        });
        AddAdvisor.setOnMouseExited((event1)->{
            AddAdvisor.setOpacity(1.0);
            AddAdvisor.setScaleX(1.0);
            AddAdvisor.setScaleY(1.0);
        });
        AddAdvisor.setOnAction((event2)->{
            try {
                Stage currentStage = (Stage) AddAdvisor.getScene().getWindow();
                currentStage.close();
                AddAdvisorApplication addAdvisorApplication = new AddAdvisorApplication();
                Stage newStage = new Stage();
                addAdvisorApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Advisor.setOnMouseEntered((event)->{
            Advisor.setOpacity(0.5);
            Advisor.setScaleX(1.1);
            Advisor.setScaleY(1.1);
        });
        Advisor.setOnMouseExited((event1)->{
            Advisor.setOpacity(1.0);
            Advisor.setScaleX(1.0);
            Advisor.setScaleY(1.0);
        });
        Advisor.setOnAction((event2)->{
            try {
                Stage currentStage = (Stage) Advisor.getScene().getWindow();
                currentStage.close();
                DashBoardApplication dashBoardApplication = new DashBoardApplication();
                Stage newStage = new Stage();
                dashBoardApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        AdvisorList.setOnMouseEntered((event)->{
            AdvisorList.setOpacity(0.5);
            AdvisorList.setScaleX(1.1);
            AdvisorList.setScaleY(1.1);
        });
        AdvisorList.setOnMouseExited((event1)->{
            AdvisorList.setOpacity(1.0);
            AdvisorList.setScaleX(1.0);
            AdvisorList.setScaleY(1.0);
        });
        AdvisorList.setOnAction((event2)->{
            try {
                Stage currentStage = (Stage) AdvisorList.getScene().getWindow();
                currentStage.close();
                AdvisorListApplication advisorListApplication = new AdvisorListApplication();
                Stage newStage = new Stage();
                advisorListApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        LogOut.setOnMouseEntered((event)->{
            LogOut.setOpacity(0.5);
            LogOut.setScaleX(1.1);
            LogOut.setScaleY(1.1);
        });
        LogOut.setOnMouseExited((event1)->{
            LogOut.setOpacity(1.0);
            LogOut.setScaleX(1.0);
            LogOut.setScaleY(1.0);
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

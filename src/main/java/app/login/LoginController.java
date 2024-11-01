package app.login;

import app.Connection.DataBaseConnection;
import app.LoadingPage.LoadingApplication;
import app.LoadingPage2.LoadingPageApplication;
import app.Student.StudentDashBoardApllication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LoginController extends DataBaseConnection {
    DataBaseConnection dataBaseConnection = new DataBaseConnection();
    @FXML
    private Button action;
    @FXML
    private TextField jobnum;
    @FXML
    private PasswordField pincode;
    @FXML
    private Label wrong;
    @FXML
    private Label action1= new Label();
    @FXML
    private void initialize() {

        action.setOnAction((event) -> {
            LoadingApplication loadingApplication = new LoadingApplication();
            if(jobnum.getText().isEmpty() || pincode.getText().isEmpty()){
                wrong.setText("Please fill all the fields");
            }

            else {
                int jobnum4 = Integer.parseInt(jobnum.getText());
                        if (dataBaseConnection.login(jobnum4, pincode.getText())){
                            Stage stage = (Stage) action.getScene().getWindow();
                            stage.close();
                            try {
                                loadingApplication.start(new Stage());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (dataBaseConnection.login2(jobnum4, pincode.getText())){
                            Stage stage = (Stage) action.getScene().getWindow();
                            stage.close();
                            try {
                                LoadingPageApplication loadingPageApplication = new LoadingPageApplication();
                                loadingPageApplication.start(new Stage());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            wrong.setText("Wrong Job Number or Pin Code");
                        }
                    }
        });
        action.setOnMouseEntered((event) -> {
            action.setOpacity(0.5);
            action.setScaleX(1.1);
            action.setScaleY(1.1);
        });
        action.setOnMouseExited((event) -> {
            action.setOpacity(1);
            action.setScaleX(1);
            action.setScaleY(1);
        });
        action1.setOnMouseClicked((event) -> {
            Stage stage = (Stage) action1.getScene().getWindow();
            stage.close();
            StudentDashBoardApllication studentDashBoardApllication = new StudentDashBoardApllication();
            try {
                studentDashBoardApllication.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}
package app.AnswerRequests;

import app.CourseEdit.CourseEditApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.net.ServerSocket;
import java.util.Optional;

public class AnswerRequestsController {
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
    private ImageView send;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField message;

    private Server server ;
    @FXML
    void initialize(){
        DashBoard();
        try {
            server = new Server(new ServerSocket(8000));

        } catch (Exception e) {
            e.printStackTrace();
        }
        vBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                scrollPane.setVvalue((Double) t1);
            }
        });

        server.ReciveMessage(vBox);

        send.setOnMouseClicked((event) -> {
            String messageToSend = message.getText();
            if(!messageToSend.isEmpty()){
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_RIGHT);
                hBox.setPadding(new Insets(5,5,5,10));
                Text text= new Text(messageToSend);
                TextFlow textFlow = new TextFlow(text);
                textFlow.setStyle("-fx-color:rgb(239,242,255)"+";"+"-fx-background-color:rgb(15, 25, 242)"+";"+"-fx-background-radius: 20px");
                textFlow.setPadding(new Insets(5,10,5,10));
                text.setFill(Color.color(0.934,0.945,0.996));
                hBox.getChildren().add(textFlow);
                vBox.getChildren().add(hBox);

                server.sendMessage(messageToSend);
                message.clear();

            }
        });
    }
    public static void addlabel(String messageFromClient,VBox vbox){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5,5,5,10));
        Text text= new Text(messageFromClient);
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-background-color:rgb(233,233,235)"+";"+"-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5,10,5,10));
        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vbox.getChildren().add(hBox);
            }
        });
    }

    @FXML
    private void DashBoard() {
        HomePage.setOnMouseClicked((event) -> {
            try {
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
                CoursesSearchApplication coursesSearchApplication = new CoursesSearchApplication();
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

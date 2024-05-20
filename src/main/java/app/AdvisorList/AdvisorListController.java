package app.AdvisorList;

import app.AddAdvisor.AddAdvisorApplication;
import app.Administrator.AdminApplication;
import app.Dashboard.DashBoardApplication;
import app.login.LoginApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class AdvisorListController {
    @FXML
    private TableView Table;
    @FXML
    private TableColumn Name;
    @FXML
    private TableColumn ID;
    @FXML
    private Button Delete;
    @FXML
    private TextField id;
    @FXML
    private Label error;
    @FXML
    private Label LogOut;
    @FXML
    private Label HomePage;
    @FXML
    private Label AdvisorList;
    @FXML
    private Label Advisor;
    @FXML
    private Label AddAdvisor;
    @FXML
    private void initialize(){
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AdvisorID, AdvisorName FROM Advisor");

            ObservableList<Advisor> data = FXCollections.observableArrayList();
            while (resultSet.next()) {
                int id = resultSet.getInt("AdvisorID");
                String name = resultSet.getString("AdvisorName");
                data.add(new Advisor(id, name));
            }
            Table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        HomePage.setOnMouseClicked((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                AdminApplication adminApplication=new AdminApplication();
                Stage newStage = new Stage();
                adminApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        AdvisorList.setOnMouseClicked((event )-> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                AdvisorListApplication advisorListApplication=new AdvisorListApplication();
                Stage newStage = new Stage();
                advisorListApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Advisor.setOnMouseClicked((e4)->{
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                DashBoardApplication dashBoardApplication=new DashBoardApplication();
                Stage newStage = new Stage();
                dashBoardApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Delete.setOnMouseEntered((event) -> {
            Delete.setOpacity(0.5);
            Delete.setScaleX(1.1);
            Delete.setScaleY(1.1);
        });
        Delete.setOnMouseExited((event1) -> {
            Delete.setOpacity(1.0);
            Delete.setScaleX(1.0);
            Delete.setScaleY(1.0);
        });
        Delete.setOnAction((event2) -> {
            if (id.getText().isEmpty()){
                error.setText("Please fill the ID field");
            }
            else {
                int id1 = Integer.parseInt(id.getText());
                //check if the advisor with the given id exists in the database
                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM Advisor WHERE AdvisorID = " + id1);
                    if(resultSet.next()) {
                        //id found, delete the advisor
                      // make confirmation alert
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Are you sure you want to delete this advisor?");
                        alert.setContentText("Choose your option.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK){
                            statement.executeUpdate("DELETE FROM Advisor WHERE AdvisorID = " + id1);
                            //refresh the table
                            ObservableList<Advisor> data = FXCollections.observableArrayList();
                            ResultSet resultSet1 = statement.executeQuery("SELECT AdvisorID, AdvisorName FROM Advisor");
                            while (resultSet1.next()) {
                                int id = resultSet1.getInt("AdvisorID");
                                String name = resultSet1.getString("AdvisorName");
                                data.add(new Advisor(id, name));
                            }
                            Table.setItems(data);
                            error.setText("Advisor with id " + id1 + " deleted successfully.");
                        }
                    } else {
                        //id not found
                        error.setText("Advisor with id " + id1 + " not found.");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
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
        HomePage.setOnMouseClicked((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                AdminApplication adminApplication=new AdminApplication();
                Stage newStage = new Stage();
                adminApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        AdvisorList.setOnMouseClicked((event )-> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                AdvisorListApplication advisorListApplication=new AdvisorListApplication();
                Stage newStage = new Stage();
                advisorListApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Advisor.setOnMouseClicked((e4)->{
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                DashBoardApplication dashBoardApplication=new DashBoardApplication();
                Stage newStage = new Stage();
                dashBoardApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        AddAdvisor.setOnMouseClicked((event)->{
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
    }

}

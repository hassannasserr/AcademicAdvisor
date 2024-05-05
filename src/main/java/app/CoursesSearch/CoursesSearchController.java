package app.CoursesSearch;

import app.Dashboard.DashBoardApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class CoursesSearchController {
    @FXML
    private Label HomePage;
    @FXML
    private Label StudentReg;
    @FXML
    private Label CourseSea;
    @FXML
    private Label EditCor;
    @FXML
    private Label AnswerReq;
    @FXML
    private Label LogOut;
    @FXML
    private TableView<Course> CourseTable;
    @FXML
    private TableColumn<Course, String> CodeCol;
    @FXML
    private TableColumn<Course, Integer> HourCol;
    @FXML
    private TableColumn<Course, String> NameCol;
    @FXML
    private TableColumn<Course, String> DepCol;
    @FXML
    private TableColumn<Course, Integer>SemsterCol;
    @FXML
    private TableColumn<Course, String> PrequesteCol;
    @FXML
    private TableColumn<Course, String> PrequesteCol2;
    @FXML
    private TableColumn<Course, String> RateCol;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<Integer> Semester;
    @FXML
    private Button Search;


    @FXML
    private void initialize() {
        DashBoard();
        Semester.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        CodeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        HourCol.setCellValueFactory(new PropertyValueFactory<>("hours"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        DepCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        SemsterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        PrequesteCol.setCellValueFactory(new PropertyValueFactory<>("prerequisite1"));
        PrequesteCol2.setCellValueFactory(new PropertyValueFactory<>("prerequisite2"));
        RateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
         Search.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 try {
                     CourseTable.getItems().clear();
                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                     Statement statement = connection.createStatement();
                     if(searchField.getText().isEmpty() && Semester.getValue() == null){
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM courses");
                         while (resultSet.next()) {
                             CourseTable.getItems().add(new Course(resultSet.getString("CourseCode"), resultSet.getInt("CourseHour"), resultSet.getString("CourseName"), resultSet.getString("CourseDepartment"), resultSet.getInt("CourseSemester"), resultSet.getString("CoursesPrequeste1"), resultSet.getString("CoursePrequeste2"), resultSet.getString("CourseRate")));
                         }
                     }
                     else if (!searchField.getText().isEmpty() && Semester.getValue() == null){
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE CourseName = '" + searchField.getText() + "'");
                         while (resultSet.next()) {
                             CourseTable.getItems().add(new Course(resultSet.getString("CourseCode"), resultSet.getInt("CourseHour"), resultSet.getString("CourseName"), resultSet.getString("CourseDepartment"), resultSet.getInt("CourseSemester"), resultSet.getString("CoursesPrequeste1"), resultSet.getString("CoursePrequeste2"), resultSet.getString("CourseRate")));
                         }
                     }
                     else if (searchField.getText().isEmpty() && Semester.getValue() != null){
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE CourseSemester = '" + Semester.getValue() + "'");
                         while (resultSet.next()) {
                             CourseTable.getItems().add(new Course(resultSet.getString("CourseCode"), resultSet.getInt("CourseHour"), resultSet.getString("CourseName"), resultSet.getString("CourseDepartment"), resultSet.getInt("CourseSemester"), resultSet.getString("CoursesPrequeste1"), resultSet.getString("CoursePrequeste2"), resultSet.getString("CourseRate")));
                         }
                     }
                     else if (!searchField.getText().isEmpty() && Semester.getValue() != null){
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE CourseName = '" + searchField.getText() + "' AND CourseSemester = '" + Semester.getValue() + "'");
                         while (resultSet.next()) {
                             CourseTable.getItems().add(new Course(resultSet.getString("CourseCode"), resultSet.getInt("CourseHour"), resultSet.getString("CourseName"), resultSet.getString("CourseDepartment"), resultSet.getInt("CourseSemester"), resultSet.getString("CoursesPrequeste1"), resultSet.getString("CoursePrequeste2"), resultSet.getString("CourseRate")));
                         }
                     }
             } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }});


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
            System.out.println("Edit Course");
        });
        AnswerReq.setOnMouseClicked((event) -> {
            System.out.println("Answer Request");
        });
        LogOut.setOnMouseClicked((event) -> {
            try {
                Stage currentStage = (Stage) HomePage.getScene().getWindow();
                currentStage.close();
                LoginApplication loginApplication = new LoginApplication();
                Stage newStage = new Stage();
                loginApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }
}

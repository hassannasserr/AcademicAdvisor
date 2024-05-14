package app.CourseSearch;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.RegistrationProcess2.RP2Application;
import app.RegistrationProcess2.RP2Controller;
import app.StudentRegisteration.StRegApplication;
import app.StudentRegisteration.StRegController;
import app.login.LoginApplication;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import app.RegistrationProcess.CourseGraph;

import java.util.Optional;

public class CourseSearchController {
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
    private TextField course;
    @FXML
    private TextArea Result;
    @FXML
    ImageView Search;
    @FXML
    private Button Export;

    @FXML
    private Button Reset;
    @FXML
    void initialize(){
        DashBoard();
        Result.setEditable(false);
        if(Result.getText().isEmpty()){
            Export.setVisible(false);
            Reset.setVisible(false);
        }
        Search.setOnMouseClicked((event) -> {
            String courseName = course.getText();
            if (courseName.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Please enter a course name");
                alert.showAndWait();
            }else {
                // Create an instance of CourseGraph
                CourseGraph courseGraph = new CourseGraph();

                // Get the prerequisites of the course
                List<String> prerequisites = courseGraph.getPrerequisites1(courseName);

                // Check if the course was not found or has no prerequisites
                if (prerequisites == null) {
                    Result.setText("Course not found.");
                } else if (prerequisites.isEmpty()) {
                    Result.setText("The course isn't prerequisite for any course.");
                } else {
                    // Convert the list of prerequisites to a string and display it in the Result text area
                    StRegController stRegController = new StRegController();
                    List<String> prerequisiteNames = new ArrayList<>();
                    for (String courseCode : prerequisites) {
                        prerequisiteNames.add(stRegController.getCourseNameByCode(courseCode));
                    }

                    StringBuilder prerequisitesString = new StringBuilder("The course is prerequisites for  \"" + courseName + "\":\n");
                    for (int i = 0; i < prerequisiteNames.size(); i++) {
                        prerequisitesString.append((i + 1) + "- " + prerequisiteNames.get(i) + "\n");
                    }

                    Result.setText(prerequisitesString.toString());
                    Export.setVisible(true);
                    Reset.setVisible(true);
                }
            }
        });
        Reset.setOnMouseClicked((event) -> {
            course.clear();
            Result.clear();
            Export.setVisible(false);
            Reset.setVisible(false);
        });
        Search.setOnMouseEntered((event) -> {
            Search.setOpacity(0.5);

        });
        Search.setOnMouseExited((event) -> {
            Search.setOpacity(1);
        });
        Export.setOnMouseEntered((event) -> {
            Export.setOpacity(0.5);
            Export.setScaleX(1.1); // Increase the width of the button by 10%
            Export.setScaleY(1.1); // Increase the height of the button by 10%
        });
        Export.setOnMouseExited((event) -> {
            Export.setOpacity(1.0);
            Export.setScaleX(1.0); // Increase the width of the button by 10%
            Export.setScaleY(1.0); // Increase the height of the button by 10%
        });
        Reset.setOnMouseEntered((event) -> {
            Reset.setOpacity(0.5);
            Reset.setScaleX(1.1); // Increase the width of the button by 10%
            Reset.setScaleY(1.1); // Increase the height of the button by 10%
        });
        Reset.setOnMouseExited((event) -> {
            Reset.setOpacity(1.0);
            Reset.setScaleX(1.0); // Increase the width of the button by 10%
            Reset.setScaleY(1.0); // Increase the height of the button by 10%
        });
        Export.setOnMouseClicked((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Export");
            alert.setHeaderText("Are you sure you want to export the result in excel sheet?");
            alert.setContentText("Choose your option.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                // Create a new workbook
                XSSFWorkbook workbook = new XSSFWorkbook();
                // Create a new sheet
                XSSFSheet sheet = workbook.createSheet("Results");
                // Split the Result text into lines
                String[] lines = Result.getText().split("\n");
                for (int i = 0; i < lines.length; i++) {
                    // Create a new row for each line
                    XSSFRow row = sheet.createRow(i);
                    // Create a new cell in the row and set its value to the line
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(lines[i]);
                }
                // Open a file chooser dialog
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
                File file = fileChooser.showSaveDialog(Export.getScene().getWindow());
                if (file != null) {
                    try (FileOutputStream outputStream = new FileOutputStream(file)) {
                        // Write the workbook to the file
                        workbook.write(outputStream);
                        // Close the workbook
                        workbook.close();
                        // Display a message to the user
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Export Result");
                        alert1.setHeaderText("Export Result");
                        alert1.setContentText("The result has been exported successfully.");
                        alert1.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

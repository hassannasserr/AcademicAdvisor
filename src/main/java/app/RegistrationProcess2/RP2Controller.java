package app.RegistrationProcess2;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CourseSearch.CourseSearchApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.RegistrationProcess.CourseGraph;
import app.RegistrationProcess.RegistrationProcessApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class RP2Controller {
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
    private ImageView Back;
    @FXML
    private Button Export;


    List<String> availableCourses = new ArrayList<>();

    int semester;

    int Hours;

    double GPA;
    @FXML
    private TextArea Coruses;
    @FXML
    private Label note1;
    @FXML
    private Label note2;
    @FXML
    private Label note3;
    @FXML
    private Button recmo;
    public void initData(List<String> availableCourses, int selectedSemester, int creditHours, double gpa) {
        this.availableCourses = availableCourses;
        this.semester = selectedSemester;
        this.Hours = creditHours;
        this.GPA = gpa;
        System.out.println("Data received: " + availableCourses + " " + selectedSemester + " " + creditHours + " " + gpa);
    }
    public RP2Controller() {
    }


    @FXML
    public void initialize() {
        DashBoard();

        recmo.setOnMouseEntered(e -> {
        recmo.setOpacity(0.5);
        recmo.setScaleX(1.1); // Increase the width of the button by 10%
        recmo.setScaleY(1.1); // Increase the height of the button by 10%
        });
        recmo.setOnMouseExited(e -> {
            recmo.setOpacity(1);
            recmo.setScaleX(1);
            recmo.setScaleY(1);
        });
        Coruses.setEditable(false);
        if (GPA < 2.0) {
            note3.setText("You only can take 12 credit hours");
        }
        else if (GPA >= 3.0) {
            note3.setText("You can take 18 credit hours");
        }
        if(semester== 1.0){
           note1.setText("You are in the same progress with Your colleagues");
        }
       else if(semester== 2){
          int H=18-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 3){
            int H=36-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 4){
            int H=54-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 5){
            int H=72-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 6){
            int H=87-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 7){
            int H=102-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
        else if(semester== 8){
            int H=117-Hours;
            note1.setText("You need to finish " +H+" Hours to meet Your colleagues");
        }
       if(GPA<2.0)
       {
           int h=93-Hours;
           double g=2.0-GPA;
           note2.setText("You need to finish be " + h+" Hours to register graduation project and you need to increase your GPA by "+g);
       }
       else {
              int h=93-Hours;
              note2.setText("You need to  " + h+" hours to register graduation project");
         }

         Coruses.setText(String.join("\n", availableCourses));
        Coruses.textProperty().addListener((observable, oldValue, newValue) -> {
            Export.setVisible(!newValue.isEmpty());
        });

        recmo.setOnAction(e -> {
            // Append the recommendation message to the Coruses text area
            Coruses.appendText("\n\nWe Recommend to Register these courses");

            // Create an instance of CourseGraph
            CourseGraph courseGraph = new CourseGraph();

            // Create a map to store the prerequisites count for each course
            Map<String, Integer> coursePrerequisitesCount = new HashMap<>();

            for (String course : availableCourses) {
                int prerequisitesCount = courseGraph.getPrerequisitesCount(course);
                // Put the course and its prerequisites count into the map
                coursePrerequisitesCount.put(course, prerequisitesCount);
            }

            // Convert the map into a list of entries
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(coursePrerequisitesCount.entrySet());

            // Sort the list in descending order by the prerequisites count
            entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            // Create a connection to the database
            try (            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            ) {
                //
                //Prepare a statement to fetch the course rate
                PreparedStatement ps = conn.prepareStatement("SELECT CourseRate FROM courses WHERE CourseName = ?");

                // Get the first 4 entries from the sorted list
                int loopLimit = GPA < 2.0 ? 4 : entries.size();

                for (int i = 0; i < loopLimit && i < entries.size(); i++) {
                    Map.Entry<String, Integer> entry = entries.get(i);
                    String courseName = entry.getKey();
                    int prerequisitesCount = entry.getValue();

                    // Set the course name parameter in the prepared statement
                    ps.setString(1, courseName);

                    // Execute the query and get the result
                    ResultSet rs = ps.executeQuery();

                    // If a result is returned, get the course rate
                    String courseRate = String.valueOf(rs.next() ? rs.getString("CourseRate") : 0);

                    // Append the course name, prerequisites count, and course rate to the Coruses text area
                    Coruses.appendText("\n" + courseName + " has " + prerequisitesCount + " prerequisites and a rate of " + courseRate);
                }
                recmo.setDisable(true);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        });
        Back.setOnMouseEntered((event) -> {
            Back.setOpacity(0.5);
            Back.setScaleX(1.1); // Increase the width of the button by 10%
            Back.setScaleY(1.1); // Increase the height of the button by 10%
        });
        Back.setOnMouseExited((event) -> {
            Back.setOpacity(1);
            Back.setScaleX(1);
            Back.setScaleY(1);
        });
        Back.setOnMouseClicked((event) -> {
            try {
                Stage currentStage = (Stage) Back.getScene().getWindow();
                currentStage.close();
                RegistrationProcessApplication registrationProcessApplication = new RegistrationProcessApplication();
                Stage newStage = new Stage();
                registrationProcessApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Export.setOnMouseEntered((event) -> {
            Export.setOpacity(0.5);
            Export.setScaleX(1.1); // Increase the width of the button by 10%
            Export.setScaleY(1.1); // Increase the height of the button by 10%
        });
        Export.setOnMouseExited((event) -> {
            Export.setOpacity(1);
            Export.setScaleX(1);
            Export.setScaleY(1);
        });
        Export.setOnAction((event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Export");
            alert.setHeaderText("Are you sure you want to export the result in excel sheet? ");
            alert.setContentText("Choose your option.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                // Create a new workbook
                XSSFWorkbook workbook = new XSSFWorkbook();
                // Create a new sheet
                XSSFSheet sheet = workbook.createSheet("Results");
                // Split the Result text into lines
                String[] lines = Coruses.getText().split("\n");
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

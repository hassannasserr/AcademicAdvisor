package app.RegistrationProcess2;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CourseSearch.CourseSearchApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.RegistrationProcess.CourseGraph;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
        Coruses.setEditable(false);
        if (GPA < 2.0) {
            note3.setText("You only can take 12 credit hours");
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
       if(GPA>2.0){
           recmo.setVisible(false);
       }
        recmo.setOnAction(e -> {
           // clear the text area
            Coruses.clear();

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

            // Get the first 4 entries from the sorted list
            for (int i = 0; i < 4 && i < entries.size(); i++) {
                Map.Entry<String, Integer> entry = entries.get(i);
                Coruses.appendText("\n" + entry.getKey() + " has " + entry.getValue() + " prerequisites");
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

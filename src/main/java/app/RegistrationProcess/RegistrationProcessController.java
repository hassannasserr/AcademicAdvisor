package app.RegistrationProcess;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CourseEdit.CourseEditApplication;
import app.CourseSearch.CourseSearchApplication;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationProcessController extends StRegController {
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
    private TextArea AvCo;
    @FXML
    private Button Done;
    @FXML
    private ComboBox<Integer> Semesters;
    @FXML
    private ComboBox<Integer> Semesters1;
    @FXML
    private Button next;
    @FXML
    private Label error;
    @FXML
    private TextField GPA;
    @FXML
    private TextField CH;
    @FXML
    private Label error1;
    @FXML
    private Label animation;

    public RegistrationProcessController() {

    }

    @FXML
    private void initialize(){
        DashBoard();
        AvCo.setEditable(false);
        Semesters.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        Semesters1.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        Done.setOnAction((event) -> {
            if (Semesters.getValue() == null) {
                error.setText("Please select a semester");
            }
            else {
                error.setText("");
                Integer selectedSemester = Semesters.getValue();
                List<String> availableCourses = new ArrayList<>();
                CourseGraph courseGraph = new CourseGraph();
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                    PreparedStatement ps = conn.prepareStatement("SELECT CourseCode FROM courses WHERE CourseSemester = ?");
                    ps.setInt(1, selectedSemester);
                    ResultSet rs = ps.executeQuery();
                    List<String> semesterCourses = new ArrayList<>();
                    while (rs.next()) {
                        semesterCourses.add(rs.getString("CourseCode"));
                    }
                    System.out.println("codeList: " + codeList);
                    System.out.println("semesterCourses: " + semesterCourses);
                    List<String> courseCodes = courseGraph.getAvailableCourses(semesterCourses, codeList);
                    for (String courseCode : courseCodes) {
                        availableCourses.add(getCourseNameByCode(courseCode));
                    }
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                AvCo.setText(String.join("\n", availableCourses));
            }
            next.setOnAction((event1) -> {
                boolean hasError = false;

                if (Semesters1.getValue() == null) {
                    error1.setText("Please select a semester");
                    hasError = true;
                }
                if (GPA.getText().isEmpty()) {
                    error1.setText("Please enter your GPA");
                    hasError = true;
                }
                if(!GPA.getText().isEmpty()){
                    try {
                        Double gpa = Double.parseDouble(GPA.getText());
                        if (gpa < 0 || gpa > 4) {
                            error1.setText("Please enter a valid GPA");
                            hasError = true;
                        }
                    } catch (NumberFormatException e) {
                        error1.setText("Please enter a valid GPA");
                        hasError = true;
                    }
                }
                if (CH.getText().trim().isEmpty() || CH.getText().isEmpty()) {
                    error1.setText("Please enter your credit hours");
                    hasError = true;
                }
                if(!CH.getText().isEmpty()){
                    try {
                        Integer creditHours = Integer.parseInt(CH.getText());
                        if (creditHours < 0 || creditHours > 143) {
                            error1.setText("Please enter a valid credit hours");
                            hasError = true;
                        }
                    } catch (NumberFormatException e) {
                        error1.setText("Please enter a valid credit hours");
                        hasError = true;
                    }
                }
                if (!hasError) {
                    // Clear the text of the animation label
                    animation.setText("");

                    // Create a string for the animation text
                    String animationText = "Processing...";

                    // Create a timeline to animate the text
                    Timeline timeline = new Timeline();
                    for (int i = 0; i < animationText.length(); i++) {
                        // Create a pause transition with a duration of 200 milliseconds
                        PauseTransition pause = new PauseTransition(Duration.millis(200));

                        // Set the action to perform after the pause
                        int finalI = i;
                        pause.setOnFinished(event3 -> {
                            // Add one character to the animation label's text
                            animation.setText(animation.getText() + animationText.charAt(finalI));
                        });

                        // Add the pause transition to the timeline
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 200), pause.getOnFinished()));
                    }

                    // Set the action to perform after the timeline
                    timeline.setOnFinished(event4 -> {
                        // Clear the error label
                        error1.setText("");

                        // Navigate to the next page here
                        // ...
                    });

                    // Start the timeline
                    timeline.play();
                    Integer selectedSemester = Semesters1.getValue();
                    List<String> availableCourses = new ArrayList<>();
                    CourseGraph courseGraph = new CourseGraph();
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                        PreparedStatement ps = conn.prepareStatement("SELECT CourseCode FROM courses WHERE CourseSemester = ?");
                        ps.setInt(1, selectedSemester);
                        ResultSet rs = ps.executeQuery();
                        List<String> semesterCourses = new ArrayList<>();
                        while (rs.next()) {
                            semesterCourses.add(rs.getString("CourseCode"));
                        }
                        System.out.println("codeList: " + codeList);
                        System.out.println("semesterCourses: " + semesterCourses);
                        List<String> courseCodes = courseGraph.getAvailableCourses(semesterCourses, codeList);
                        for (String courseCode : courseCodes) {
                            availableCourses.add(getCourseNameByCode(courseCode));
                        }
                        rs.close();
                        ps.close();
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    // after the animation ended navigate to the next page
                    // after the animation ended navigate to the next page
                    try {
                        Stage currentStage = (Stage) HomePage.getScene().getWindow();
                        currentStage.close();

                        // Load the FXML file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/RP2/RP2.fxml"));
                        Parent root = loader.load();

                        // Get the controller
                        RP2Controller rp2Controller = loader.getController();

                        // Pass the parameters
                        rp2Controller.initData(availableCourses, selectedSemester, Integer.parseInt(CH.getText()), Double.parseDouble(GPA.getText()));

                        // Initialize the controller
                        rp2Controller.initialize();

                        // Show the scene
                        Stage newStage = new Stage();
                        newStage.setScene(new Scene(root));
                        newStage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });

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

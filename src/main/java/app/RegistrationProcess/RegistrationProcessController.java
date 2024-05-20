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
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView Back;

    public RegistrationProcessController() {

    }

    @FXML
    private void initialize(){
        DashBoard();
        AvCo.setEditable(false);
        next.setOnMouseEntered((event) -> {
            next.setOpacity(0.5);
            next.setScaleX(1.1); // Increase the width of the button by 10%
            next.setScaleY(1.1); // Increase the height of the button by 10%
        });
        next.setOnMouseExited((event) -> {
            next.setOpacity(1);
            next.setScaleX(1);
            next.setScaleY(1);
        });
        Done.setOnMouseEntered((event) -> {
            Done.setOpacity(0.5);
            Done.setScaleX(1.1); // Increase the width of the button by 10%
            Done.setScaleY(1.1); // Increase the height of the button by 10%
        });
        Done.setOnMouseExited((event) -> {
            Done.setOpacity(1);
            Done.setScaleX(1);
            Done.setScaleY(1);
        });
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
        });
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
                StRegApplication stRegApplication = new StRegApplication();
                Stage newStage = new Stage();
                stRegApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
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

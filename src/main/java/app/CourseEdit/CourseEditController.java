package app.CourseEdit;

import app.AnswerRequests.AnswerRequestsApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.StudentRegisteration.StRegApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class CourseEditController {
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
    private Button editbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button viewbtn;
    @FXML
    private TextField entercode;
    @FXML
    private TextField hourfield;
    @FXML
    private TextField namefield;
    @FXML
    private TextField depart;
    @FXML
    private TextField semesterfield;
    @FXML
    private TextField pre1;
    @FXML
    private TextField pre2;
    @FXML
    private TextField ratefield;
    @FXML
    private TextField codefield;
    @FXML
    private Label success;
    @FXML
    private Label error;
    @FXML
    private void showCourseData() {
        String courseCode = entercode.getText();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE CourseCode = '" + courseCode + "'");
            if (resultSet.next()) {
                // Display the course data in your text fields here
                codefield.setText(resultSet.getString("CourseCode"));
                hourfield.setText(resultSet.getString("CourseHour"));
                namefield.setText(resultSet.getString("CourseName"));
                depart.setText(resultSet.getString("CourseDepartment"));
                semesterfield.setText(resultSet.getString("CourseSemester"));
                pre1.setText(resultSet.getString("CoursesPrequeste1"));
                pre2.setText(resultSet.getString("CoursePrequeste2"));
                ratefield.setText(resultSet.getString("CourseRate"));
                error.setText("");
            } else {
                error.setText("No course found with code " + courseCode);
                success.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void resetFields() {
        // Clear all text fields
        codefield.clear();
        hourfield.clear();
        namefield.clear();
        depart.clear();
        semesterfield.clear();
        pre1.clear();
        pre2.clear();
        ratefield.clear();

        // Clear labels
        error.setText("");
        success.setText("");
    }
    private void addCourse() {
        String courseCode = codefield.getText();
        String courseHour = hourfield.getText();
        String courseName = namefield.getText();
        String courseDepartment = depart.getText();
        String courseSemester = semesterfield.getText();
        String coursePrequeste1 = pre1.getText();
        String coursePrequeste2 = pre2.getText();
        String courseRate = ratefield.getText();

        // Check if any of the fields are empty
        if (courseCode.isEmpty() || courseHour.isEmpty() || courseName.isEmpty() || courseDepartment.isEmpty() || courseSemester.isEmpty() || (coursePrequeste1 != null && coursePrequeste1.isEmpty()) || (coursePrequeste2 != null && coursePrequeste2.isEmpty()) || courseRate.isEmpty()) {
            error.setText("Please fill the data");
            success.setText("");
            return;
        }
        // check the Course Code if it already found in the database or not
try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM courses WHERE CourseCode = '" + courseCode + "'");
            if (resultSet.next()) {
                error.setText("Course with code " + courseCode + " already exists");
                success.setText("");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        int parsedSemester;
        int parsedCourseHour;
        try {
            parsedSemester = Integer.parseInt(courseSemester);
            parsedCourseHour = Integer.parseInt(courseHour);
        } catch (NumberFormatException e) {
            error.setText("Invalid number format");
            success.setText("");
            return;
        }

        // Check if the input is valid
        if (!isValid(parsedSemester, coursePrequeste1, coursePrequeste2, parsedCourseHour, courseRate)) {
            error.setText("Invalid input");
            success.setText("");

        }
        else if (coursePrequeste1.equals("None") && coursePrequeste2.equals("None")) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                PreparedStatement statement = conn.prepareStatement("INSERT INTO courses (CourseCode, CourseHour, CourseName, CourseDepartment, CourseSemester, CourseRate) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setString(1, courseCode);
                statement.setInt(2, Integer.parseInt(courseHour));
                statement.setString(3, courseName);
                statement.setString(4, courseDepartment);
                statement.setInt(5, Integer.parseInt(courseSemester));
                statement.setString(6, courseRate);
                statement.executeUpdate();
                resetFields();
                success.setText(courseName + " is added");
                error.setText("");
                resetFields();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(coursePrequeste1.equals("None")){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                PreparedStatement statement = conn.prepareStatement("INSERT INTO courses (CourseCode, CourseHour, CourseName, CourseDepartment, CourseSemester, CoursePrequeste2, CourseRate) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, courseCode);
                statement.setInt(2, Integer.parseInt(courseHour));
                statement.setString(3, courseName);
                statement.setString(4, courseDepartment);
                statement.setInt(5, Integer.parseInt(courseSemester));
                statement.setString(6, coursePrequeste2);
                statement.setString(7, courseRate);
                statement.executeUpdate();
                resetFields();
                success.setText(courseName + " is added");
                error.setText("");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(coursePrequeste2.equals("None")){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                PreparedStatement statement = conn.prepareStatement("INSERT INTO courses (CourseCode, CourseHour, CourseName, CourseDepartment, CourseSemester, CoursesPrequeste1, CourseRate) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, courseCode);
                statement.setInt(2, Integer.parseInt(courseHour));
                statement.setString(3, courseName);
                statement.setString(4, courseDepartment);
                statement.setInt(5, Integer.parseInt(courseSemester));
                statement.setString(6, coursePrequeste1);
                statement.setString(7, courseRate);
                statement.executeUpdate();
                resetFields();
                success.setText(courseName + " is added");
                error.setText("");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        else if(coursePrequeste1.equals(coursePrequeste2)){
            error.setText("The prerequisites can't be the same");
            success.setText("");
        }

        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to add this course?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                    PreparedStatement statement = conn.prepareStatement("INSERT INTO courses (CourseCode, CourseHour, CourseName, CourseDepartment, CourseSemester, CoursesPrequeste1, CoursePrequeste2, CourseRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    statement.setString(1, courseCode);
                    statement.setInt(2, Integer.parseInt(courseHour));
                    statement.setString(3, courseName);
                    statement.setString(4, courseDepartment);
                    statement.setInt(5, Integer.parseInt(courseSemester));
                    statement.setString(6, coursePrequeste1);
                    statement.setString(7, coursePrequeste2);
                    statement.setString(8, courseRate);
                    statement.executeUpdate();
                    resetFields();
                    success.setText(courseName + " is added");
                    error.setText("");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resetFields();
            }
        }
    }
    private void editcourse() {

            String courseCode = codefield.getText();
            String courseHour = hourfield.getText();
            String courseName = namefield.getText();
            String courseDepartment = depart.getText();
            String courseSemester = semesterfield.getText();
            String coursePrequeste1 = pre1.getText();
            String coursePrequeste2 = pre2.getText();
            String courseRate = ratefield.getText();

            // Check if any of the fields are empty
            if (courseCode.isEmpty() || courseHour.isEmpty() || courseName.isEmpty() || courseDepartment.isEmpty() || courseSemester.isEmpty() || (coursePrequeste1 != null && coursePrequeste1.isEmpty()) || (coursePrequeste2 != null && coursePrequeste2.isEmpty()) || courseRate.isEmpty()) {
                error.setText("Please fill the data");
                success.setText("");
                return;
            }

            int parsedSemester;
            int parsedCourseHour;
            try {
                parsedSemester = Integer.parseInt(courseSemester);
                parsedCourseHour = Integer.parseInt(courseHour);
            } catch (NumberFormatException e) {
                error.setText("Invalid number format");
                success.setText("");
                return;
            }

            // Check if the input is valid
            if (!isValid(parsedSemester, coursePrequeste1, coursePrequeste2, parsedCourseHour, courseRate)) {
                error.setText("Invalid input");
                success.setText("");
                return;
            }

        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to edit this course?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                    PreparedStatement statement = conn.prepareStatement("UPDATE courses SET CourseHour = ?, CourseName = ?, CourseDepartment = ?, CourseSemester = ?, CoursesPrequeste1 = ?, CoursePrequeste2 = ?, CourseRate = ? WHERE CourseCode = ?");
                    statement.setInt(1, Integer.parseInt(courseHour));
                    statement.setString(2, courseName);
                    statement.setString(3, courseDepartment);
                    statement.setInt(4, Integer.parseInt(courseSemester));
                    statement.setString(5, coursePrequeste1);
                    statement.setString(6, coursePrequeste2);
                    statement.setString(7, courseRate);
                    statement.setString(8, courseCode);
                    statement.executeUpdate();
                    resetFields();
                    success.setText(courseName + " is edited");
                    error.setText("");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                resetFields();
            }
        }
    }
    private void deleteCourse() {
        String courseCode = entercode.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to delete this course?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
                PreparedStatement statement = conn.prepareStatement("DELETE FROM courses WHERE CourseCode = ?");
                statement.setString(1, courseCode);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    success.setText("Course with code " + courseCode + " is deleted");
                    error.setText("");
                } else {
                    error.setText("No course found with code " + courseCode);
                    success.setText("");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                // After deleting the course, reset the fields
                resetFields();
            }
        } else {
            // Reset the fields if the user cancels the operation
            resetFields();
        }
    }
    private boolean isValid(int semester, String prerequisite1, String prerequisite2, int courseHour, String rate) {
        // Check if semester is between 1 and 8

        return true;
    }
    @FXML
    void initialize() {
        DashBoard();
        viewbtn.setOnAction((event) -> showCourseData());
        viewbtn.setOnMouseEntered((event) -> {
            viewbtn.setOpacity(0.5);
            viewbtn.setScaleX(1.1); // Increase the width of the button by 10%
            viewbtn.setScaleY(1.1); // Increase the height of the button by 10%
        });
        viewbtn.setOnMouseExited((event) -> {viewbtn.setOpacity(1);
            viewbtn.setScaleX(1);
            viewbtn.setScaleY(1);
        });
        addbtn.setOnMouseEntered((event) -> {
            addbtn.setOpacity(0.5);
            addbtn.setScaleX(1.1); // Increase the width of the button by 10%
            addbtn.setScaleY(1.1); // Increase the height of the button by 10%
        });
        addbtn.setOnMouseExited((event) -> {addbtn.setOpacity(1);
            addbtn.setScaleX(1);
            addbtn.setScaleY(1);
        });
        deletebtn.setOnMouseEntered((event) -> {
            deletebtn.setOpacity(0.5);
            deletebtn.setScaleX(1.1); // Increase the width of the button by 10%
            deletebtn.setScaleY(1.1); // Increase the height of the button by 10%
        });
        deletebtn.setOnMouseExited((event) -> {deletebtn.setOpacity(1);
            deletebtn.setScaleX(1);
            deletebtn.setScaleY(1);
        });
        editbtn.setOnMouseEntered((event) -> {
            editbtn.setOpacity(0.5);
            editbtn.setScaleX(1.1); // Increase the width of the button by 10%
            editbtn.setScaleY(1.1); // Increase the height of the button by 10%
        });
        editbtn.setOnMouseExited((event) -> {editbtn.setOpacity(1);
            editbtn.setScaleX(1);
            editbtn.setScaleY(1);
        });
        addbtn.setOnAction((event) -> addCourse());
        deletebtn.setOnAction((event) -> deleteCourse());
        editbtn.setOnAction((event) -> editcourse());
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

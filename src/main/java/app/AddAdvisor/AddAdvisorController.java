package app.AddAdvisor;

import app.Administrator.AdminApplication;
import app.AdvisorList.AdvisorListApplication;
import app.Dashboard.DashBoardApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
public class AddAdvisorController {
    @FXML
    private TextField name;
    @FXML
    private TextField SSN;
    @FXML
    private TextField ScientificDegree;
    @FXML
    private TextField GraduationYear;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField id ;
    @FXML
    private TextField Password ;
    @FXML
    private Label error;
    @FXML
    private Label Success;
    @FXML
    private Button reset;
    @FXML
    private Button Add;
    @FXML
    private Button PwdGen;
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
    void initialize(){
        PwdGen.setOnAction((event)->{
            String password = "";
            String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String specialCharacters = "!@#$";
            String numbers = "1234567890";
            String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
            for(int i = 0; i < 8; i++) {
                int index = (int)(Math.random() * combinedChars.length());
                password += combinedChars.charAt(index);
            }
            Password.setText(password);
        });
        reset.setOnAction((event)->{
            name.setText("");
            SSN.setText("");
            ScientificDegree.setText("");
            GraduationYear.setText("");
            phone.setText("");
            email.setText("");
            id.setText(generateRandomId());
            Password.setText("");
            error.setText("");
            Success.setText("");
        });
        Add.setOnAction((event)->{
            if(name.getText().isEmpty() || SSN.getText().isEmpty() || ScientificDegree.getText().isEmpty() || GraduationYear.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty() || id.getText().isEmpty() || Password.getText().isEmpty()){
                error.setText("Please fill all the fields");
            }
            else {
                error.setText("");

                // Validate email and phone number
                if (!isValidEmail(email.getText())) {
                    error.setText("Invalid email");
                    return;
                }

                // Check if ID, email, phone number, or SSN already exist in the database
                if (isIdExistInDatabase(id.getText()) || isEmailExistInDatabase(email.getText()) || isPhoneNumberExistInDatabase(phone.getText()) || isSsnExistInDatabase(SSN.getText())) {
                    error.setText("ID, email, phone number, or SSN already exist in the database.");
                    return;
                }

                // Validate graduation year and scientific degree
                if (!isValidGraduationYear(GraduationYear.getText())) {
                    error.setText("Invalid graduation year");
                    return;
                }

                // Add the advisor to the database
                addAdvisorToDatabase(name.getText(), SSN.getText(), ScientificDegree.getText(), GraduationYear.getText(), phone.getText(), email.getText(), id.getText(), Password.getText());
                error.setText("");
                Success.setText("Advisor Added Successfully");
            }
        });
        id.setEditable(false);
        id.setText(generateRandomId());
        PwdGen.setOnMouseEntered((event)->{
            PwdGen.setOpacity(0.5);
            PwdGen.setScaleX(1.1);
            PwdGen.setScaleY(1.1);
        });
        PwdGen.setOnMouseExited((event)->{
            PwdGen.setOpacity(1);
            PwdGen.setScaleX(1);
            PwdGen.setScaleY(1);
        });
        reset.setOnMouseEntered((event)->{
            reset.setOpacity(0.5);
            reset.setScaleX(1.1);
            reset.setScaleY(1.1);
        });
        reset.setOnMouseExited((event)->{
            reset.setOpacity(1);
            reset.setScaleX(1);
            reset.setScaleY(1);
        });
        Add.setOnMouseEntered((event)->{
            Add.setOpacity(0.5);
            Add.setScaleX(1.1);
            Add.setScaleY(1.1);
        });
        Add.setOnMouseExited((event)->{
            Add.setOpacity(1);
            Add.setScaleX(1);
            Add.setScaleY(1);
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

    private String generateRandomId() {
        Random random = new Random();
        int id = random.nextInt(90000) + 10000; // This will generate a random number between 10000 and 99999
        return String.valueOf(id);
    }

    private boolean isValidEmail(String email) {
        // This is a simple email validation. You can use a more complex one if you want.
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }
    private boolean isValidGraduationYear(String graduationYear) {
     int year = Integer.parseInt(graduationYear);
        if (year < 2015 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }
        return true;
    }
    private boolean isIdExistInDatabase(String id) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM Advisor WHERE AdvisorID = '" + id + "'").next();
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean isEmailExistInDatabase(String email) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM Advisor WHERE Email = '" + email + "'").next();
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean isPhoneNumberExistInDatabase(String phoneNumber) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM Advisor WHERE Phone = '" + phoneNumber + "'").next();
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean isSsnExistInDatabase(String ssn) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM Advisor WHERE SSN = '" + ssn + "'").next();
        }
        catch (Exception e){
            return false;
        }

    }



    private void addAdvisorToDatabase(String name, String ssn, String scientificDegree, String graduationYear, String phone, String email, String id, String password) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Advisor (AdvisorID, AdvisorName, AdvisorSSN, `Scientifc Degree`, GraduationYear, Phone, Email, AdvisorPwd) VALUES (" + id + ", '" + name + "', '" + ssn + "', '" + scientificDegree + "', '" + graduationYear + "', '" + phone + "', '" + email + "', '" + password + "')");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

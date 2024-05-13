package app.StudentRegisteration;
import app.AnswerRequests.AnswerRequestsApplication;
import app.Connection.DataBaseConnection;
import app.CourseSearch.CourseSearchApplication;
import app.CoursesSearch.CoursesSearchApplication;
import app.Dashboard.DashBoardApplication;
import app.RegistrationProcess.RegistrationProcessApplication;
import app.login.LoginApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class StRegController {
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
    private Button ReadExcell;
    @FXML
    private TextArea Rec;
    @FXML
    private Label error;
    @FXML
    private TextField StID;
    @FXML
    private Button Next;
    @FXML
    private Button tryAgain;
    @FXML
    private Button nextpage;
   public static List<String> codeList = new ArrayList<>();
   public List<String> gradeList = new ArrayList<>();
    @FXML
    private void initialize(){
        DashBoard();
        ReadExcell.setOnAction(e -> openFileChooser());
        Rec.setEditable(false);
        Next.setOnAction(e -> {
            if (codeList.isEmpty() || gradeList.isEmpty()||StID.getText().isEmpty()) {
                error.setText("Please select an excel file first And Enter Student ID");
            }
            else if (codeList.size() != gradeList.size()) {
                error.setText("Please make sure that the number of courses and grades are equal");
            }


            else if (!StID.getText().matches("[0-9]+")) {
                error.setText("Please enter a valid student ID");
            }


            else {
                StringBuilder sb = new StringBuilder();
                for (String code : codeList) {
                    String courseName = getCourseNameByCode(code);
                    sb.append("Code: ").append(code).append(", Course Name: ").append(courseName).append("\n");
                }
                sb.append("Grade List: ").append(gradeList.toString());
                Rec.setText(sb.toString());
            }
        });
        tryAgain.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are you sure you want to try again?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                codeList.clear();
                gradeList.clear();
                Rec.clear();
                error.setText("");
                StID.clear();
            }
        });
        nextpage.setOnAction(e -> {
            if (codeList.isEmpty() || gradeList.isEmpty()||StID.getText().isEmpty()) {
                error.setText("Please select an excel file first And Enter Student ID");
            } else {
                try {
                    Stage currentStage = (Stage) LogOut.getScene().getWindow();
                    currentStage.close();
                    RegistrationProcessApplication registrationProcessApplication = new RegistrationProcessApplication();
                    Stage newStage = new Stage();
                    registrationProcessApplication.start(newStage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    @FXML
    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            readExcelFile(selectedFile);
        }
    }


    private  void readExcelFile(File file) {
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            boolean firstRow = true;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // Skip the first row (header row)
                if (firstRow) {
                    firstRow = false;
                    continue;
                }

                Cell codeCell = currentRow.getCell(0);
                Cell gradeCell = currentRow.getCell(1);

                if (codeCell != null && codeCell.getCellType() == CellType.STRING) {
                    codeList.add(codeCell.getStringCellValue());
                }

                if (gradeCell != null && gradeCell.getCellType() == CellType.STRING) {
                    gradeList.add(gradeCell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public String getCourseNameByCode(String code) {
        String courseName = "";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            PreparedStatement ps = conn.prepareStatement("SELECT CourseName FROM courses WHERE CourseCode = ?");
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                courseName = rs.getString("CourseName");
            } else {
                courseName = code + " Code Not Found";
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseName;
    }
    @FXML
    private void DashBoard(){
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
                LoginApplication loginApplication = new LoginApplication();
                Stage newStage = new Stage();
                loginApplication.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }        });
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


module com.example.academicadvisor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    opens app.login to javafx.fxml;
    opens app.Dashboard to javafx.fxml;
    opens app.Student to javafx.fxml;
    opens app.LoadingPage to javafx.fxml;
    opens app.Connection to javafx.fxml;
    opens app.StudentRegisteration to javafx.fxml;
    opens app.CoursesSearch to javafx.fxml;
    opens app.CourseEdit to javafx.fxml;
    opens app.AnswerRequests to javafx.fxml;
    opens app.RegistrationProcess to javafx.fxml;
    opens app.RegistrationProcess2 to javafx.fxml;
    opens app.CourseSearch to javafx.fxml;
    exports app.login;
    exports app.CoursesSearch;
    exports app.Dashboard;
    exports app.Student;
    exports app.LoadingPage;
    exports app.Connection;
    exports app.StudentRegisteration;
    exports app.CourseEdit;
    exports app.AnswerRequests;
    exports app.RegistrationProcess;
    exports app.RegistrationProcess2;
    exports app.CourseSearch;

}
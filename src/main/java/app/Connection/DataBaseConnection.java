package app.Connection;
import app.CoursesSearch.Course;

import java.sql.*;
public class DataBaseConnection {
    public static void connect() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(int jobnum4, String pincode) {
       boolean login = false;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Advisor WHERE AdvisorID = '" + jobnum4+ "' AND AdvisorPwd = '" + pincode + "'");
            if (resultSet.next()) {
                login = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }



}
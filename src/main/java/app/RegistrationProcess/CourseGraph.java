package app.RegistrationProcess;
import java.sql.*;
import java.util.*;

public class CourseGraph {
    private Map<String, List<String>> adjList;

    public CourseGraph() {
        this.adjList = new HashMap<>();
        adjList.put("CCS1101", Arrays.asList("CCS1302","CCS1001","CCS1102"));
        adjList.put("CIS1000", Arrays.asList("CSE2001"));
        adjList.put("CSE2001", Arrays.asList("CCS4502"));
        adjList.put("CCS1302", Arrays.asList("CCS2303","CCS2201","CIS2101","CCS3002"));
        adjList.put("CCS1001", Arrays.asList("CCS3601","CCS3402"));
        adjList.put("CCS1102", Arrays.asList("CCS2103"));
        adjList.put("CCS2303", Arrays.asList("CCS2401","CSE2001","CCS2304","CCS3202","CCS3003"));
        adjList.put("CCS2201", Arrays.asList("CCY2001"));
        adjList.put("CIS2101", Arrays.asList("CCS2305"));
        adjList.put("CCS3402", Arrays.asList("CCS4306"));
        adjList.put("CCS2103", Arrays.asList("CCS3202","CCS3203"));
        adjList.put("CCS2401", Arrays.asList("CCS3601","CCS3203","CCS3501","CCS3403"));
        adjList.put("CCS3202", Arrays.asList("CCS4306"));
        adjList.put("CCY2001", Arrays.asList("CCS4204"));
        adjList.put("CCS3203", Arrays.asList("CCS4204"));
        adjList.put("UNR1403", Arrays.asList("UNR2101","UNR1407"));
        adjList.put("EBA1203", Arrays.asList("EBA1204"));
        adjList.put("EBA1204", Arrays.asList("EBA2204","EBA2203","EBA3202"));
        adjList.put("EBA2204", Arrays.asList("CCS3501","CCS3002"));
        adjList.put("EBA2203", Arrays.asList("CCS3003"));
        adjList.put("EBA1110", Arrays.asList("EBA1106"));
    }

    public void addCourse(String course) {
        adjList.putIfAbsent(course, new ArrayList<>());
    }

    public void addPrerequisite(String course, String prerequisite) {
        addCourse(course);
        addCourse(prerequisite);
        adjList.get(prerequisite).add(course);
    }

    public List<String> getPrerequisites(String course) {
        return adjList.getOrDefault(course, new ArrayList<>());
    }
    public List<String> getAvailableCourses(List<String> semesterCourses, List<String> codeList) {
        List<String> availableCourses = new ArrayList<>();
        for (String courseCode : codeList) {
            List<String> codeListCopy = new ArrayList<>(codeList);
            Set<String> processedCourses = new HashSet<>();
            canTakeCourse(courseCode, codeListCopy, processedCourses);
            for (String semesterCourse : semesterCourses) {
                if (processedCourses.contains(semesterCourse)) {
                    availableCourses.add(semesterCourse);
                }
            }
        }
        List<String> unavailableCourses = new ArrayList<>(semesterCourses);
        unavailableCourses.removeAll(availableCourses);
        return unavailableCourses;
    }

    //please make function that take a string name of course then get it's code from database then return how many prerequisites it has
    public int getPrerequisitesCount(String courseName) {
        int count = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CourseCode FROM courses WHERE CourseName = '" + courseName + "'");
            if (resultSet.next()) {
                String courseCode = resultSet.getString("CourseCode");
                List<String> prerequisites = getPrerequisites(courseCode);
                count = prerequisites.size();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    // make function that take a course name then convert it to course code then return it's prerequisites
    public List<String> getPrerequisites1(String courseName) {
        List<String> prerequisites = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_schema", "root", "DmjJ8GE_ps.up4J");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CourseCode FROM courses WHERE CourseName = '" + courseName + "'");
            if (resultSet.next()) {
                String courseCode = resultSet.getString("CourseCode");
                prerequisites = getPrerequisites(courseCode);
                if (prerequisites.isEmpty()) {
                    prerequisites = Collections.emptyList(); // return an empty list when the course has no prerequisites
                }
            } else {
                prerequisites = null; // return null when the course is not found in the database
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prerequisites;
    }

    private List<String> canTakeCourse(String courseCode, List<String> codeList, Set<String> processedCourses) {
        List<String> allPrerequisites = new ArrayList<>();
        if (!processedCourses.contains(courseCode)) {
            processedCourses.add(courseCode);
            List<String> prerequisites = getPrerequisites(courseCode);
            if (prerequisites != null) {
                allPrerequisites.addAll(prerequisites);
                for (String prerequisite : prerequisites) {
                    if (!processedCourses.contains(prerequisite)) {
                        codeList.add(prerequisite);
                        allPrerequisites.addAll(canTakeCourse(prerequisite, codeList, processedCourses));
                    }
                }
            }
        }

        return allPrerequisites;
    }

}

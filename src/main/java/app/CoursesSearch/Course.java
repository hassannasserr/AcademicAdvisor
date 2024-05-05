package app.CoursesSearch;

public class Course {
    private String code;
    private Integer hours;
    private String name;
    private String department;
    private Integer semester;
    private String prerequisite1;
    private String prerequisite2;
    private String rate;

    public Course(String code, Integer hours, String name, String department, Integer semester, String prerequisite1, String prerequisite2, String rate) {
        this.code = code;
        this.hours = hours;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.prerequisite1 = prerequisite1;
        this.prerequisite2 = prerequisite2;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public Integer getHours() {
        return hours;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getPrerequisite1() {
        return prerequisite1;
    }

    public String getPrerequisite2() {
        return prerequisite2;
    }

    public String getRate() {
        return rate;
    }
}

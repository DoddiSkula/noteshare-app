package is.hi.noteshare.data.models;

public class Course {
    private int id;
    private String shortName;
    private String longName;
    private String schoolName;

    public Course(int courseId, String courseShortName, String courseLongName, String courseSchoolName) {
        id = courseId;
        shortName = courseShortName;
        longName = courseLongName;
        schoolName = courseSchoolName;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }
    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

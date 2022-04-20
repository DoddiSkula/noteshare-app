package is.hi.noteshare.data.models;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("id")
    private int id;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("longName")
    private String longName;

    public Course(int courseId, String courseShortName, String courseLongName, String courseSchoolName) {
        id = courseId;
        shortName = courseShortName;
        longName = courseLongName;
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

}

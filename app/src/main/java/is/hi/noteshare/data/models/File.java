package is.hi.noteshare.data.models;

import java.util.Date;

public class File {
    private int id;
    private Date date;
    private String title;
    private String description;

    public File(int fileId, Date fileDate, String fileTitle, String fileDescription) {
        id = fileId;
        date = fileDate;
        title = fileTitle;
        description = fileDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package is.hi.noteshare.data.models;

public class File {
    private long id;
    private String date;
    private String title;
    private String description;
    private long user;

    public File(long fileId, String fileDate, String fileTitle, String fileDescription, long userId) {
        id = fileId;
        date = fileDate;
        title = fileTitle;
        description = fileDescription;
        user = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}

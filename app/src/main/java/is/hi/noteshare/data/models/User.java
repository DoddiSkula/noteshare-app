package is.hi.noteshare.data.models;

import java.util.List;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private List<Course> courses;

    public User(int userId, String username, String userEmail, String userPassword) {
        id = userId;
        this.username = username;
        email = userEmail;
        password = userPassword;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
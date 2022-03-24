package is.hi.noteshare.data.models;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int userId, String userName, String userEmail, String userPassword) {
        id = userId;
        name = userName;
        email = userEmail;
        password = userPassword;
    }

    public User() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
}
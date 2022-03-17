package is.hi.noteshare.service;

import java.util.List;

import is.hi.noteshare.entities.User;

public interface UserService {
    void login(User user);
    void logout(User user);
    void signup(User user);
    List<User> getUsers();
    User getUser(long id);
}

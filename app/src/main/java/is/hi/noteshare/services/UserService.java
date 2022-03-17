package is.hi.noteshare.services;

import java.util.List;

import is.hi.noteshare.data.models.User;

public interface UserService {
    void login(User user);
    void logout(User user);
    void signup(User user);
    List<User> getUsers();
    User getUser(long id);
}

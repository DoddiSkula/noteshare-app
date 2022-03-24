package is.hi.noteshare.services;

import java.util.List;

import is.hi.noteshare.data.models.User;

public interface UserService {
    User login(String email, String password);

    void logout(User user);

    void signup(User user);

    List<User> getUsers();

    User getUser(long id);
}

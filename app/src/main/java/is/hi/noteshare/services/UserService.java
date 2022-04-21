package is.hi.noteshare.services;

import is.hi.noteshare.data.models.User;

public interface UserService {
    void logout(User user);

    void storeUser(User user);

    User getStoredUser();
}

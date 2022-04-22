package is.hi.noteshare.services;

import is.hi.noteshare.data.models.User;

public interface UserService {
    void storeUser(User user);

    User getStoredUser();
}

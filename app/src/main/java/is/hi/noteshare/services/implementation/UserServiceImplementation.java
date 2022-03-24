package is.hi.noteshare.services.implementation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.DataService;
import is.hi.noteshare.services.Network;
import is.hi.noteshare.services.UserService;

public class UserServiceImplementation implements UserService {
    private final Network mNetwork;
    private final DataService mDataService;

    public UserServiceImplementation() {
        this.mNetwork = new NetworkImplementation();
        this.mDataService = new DataServiceImplementation();
    }

    @Override
    public User login(String email, String password) {
        User adminUser = this.getUser(0);
        if (adminUser.getEmail().equals(email) && adminUser.getPassword().equals(password)) {
            return adminUser;
        }
        return null;
    }

    @Override
    public void logout(User user) {

    }

    @Override
    public void signup(User user) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(long id) {
        JSONObject userJson = new JSONObject();
        User user = new User();

        try {
            userJson = mNetwork.getUser();
            user = mDataService.JsonToUser(userJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}

package is.hi.noteshare.services.implementation;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.UserService;

public class UserServiceImplementation implements UserService {
    private Context mContext;

    public UserServiceImplementation(Context context) {
        this.mContext = context;
    }

    @Override
    public void storeUser(User user) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("NoteShare", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("USER", json);
        editor.apply();
    }

    @Override
    public User getStoredUser() {
        SharedPreferences sharedPref = mContext.getSharedPreferences("NoteShare", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("USER", "");
        return gson.fromJson(json, User.class);
    }
}

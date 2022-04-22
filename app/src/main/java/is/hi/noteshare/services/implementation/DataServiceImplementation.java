package is.hi.noteshare.services.implementation;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.DataService;

public class DataServiceImplementation implements DataService {

    @SuppressLint("SimpleDateFormat")
    @Override
    public File JsonToFile(JSONObject json) throws JSONException {
      return null;
    }

    @Override
    public User JsonToUser(String userInfo) {
        Gson gson = new Gson();
        Type Type = new TypeToken<User>() {
        }.getType();
        return gson.fromJson(userInfo, Type);
    }

    @Override
    public Course JsonToCourse(JSONObject json) throws JSONException {
        return null;
    }
}
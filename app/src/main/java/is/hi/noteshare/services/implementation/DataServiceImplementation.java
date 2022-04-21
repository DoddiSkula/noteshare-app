package is.hi.noteshare.services.implementation;

import android.annotation.SuppressLint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.DataService;

public class DataServiceImplementation implements DataService {

    @SuppressLint("SimpleDateFormat")
    @Override
    public File JsonToFile(JSONObject json) throws JSONException {
        int id = json.getInt("id");
        String strdate = json.getString("date");
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String title = json.getString("title");
        String description = json.getString("description");
        String type = json.getString("type");
        return new File(id, date, title, description);
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
        int id = json.getInt("id");
        String shortName = json.getString("shortName");
        String longName = json.getString("longName");
        String schoolName = json.getString("schoolName");
        return new Course(id, shortName, longName, schoolName);
    }
}
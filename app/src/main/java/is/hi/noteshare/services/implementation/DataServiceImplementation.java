package is.hi.noteshare.services.implementation;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

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
        return new File(id, date, title,description,type);
    }

    @Override
    public User JsonToUser(JSONObject json) throws JSONException{
        int id = json.getInt("id");
        String name = json.getString("name");
        String email = json.getString("email");
        String password = json.getString("password");
        return new User(id, name, email, password);
    }

    @Override
    public Course JsonToCourse(JSONObject json) throws JSONException{
        int id = json.getInt("id");
        String shortName = json.getString("shortname");
        String longName = json.getString("longname");
        String schoolName = json.getString("schoolname");
        return new Course(id, shortName, longName, schoolName);
    }
}
package is.hi.noteshare.services.implementation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.text.SimpleDateFormat;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.DataService;

public class DataServiceImplementation implements DataService {
    @Override
    public File JsonToFile(JSONObject json) throws JSONException {
        int id = json.getInt("id");
        String strdate = json.getString("Date");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strdate);
        String title = json.getString("Title");
        String description = json.getString("Description");
        String type = json.getString("Type");
        File file = new File(id,date, title,description,type);
        return file;
    }
    @Override
    public User JsonToUser(JSONObject json) throws JSONException{
        int id = json.getInt("id");
        String name = json.getString("Name");
        String email = json.getString("Email");
        String password = json.getString("Password");
        User user = new User(id, name, email, password);
        return user;
    }
    @Override
    public Course JsonToCourse(JSONObject json) throws JSONException{
        int id = json.getInt("id");
        String shortName = json.getString("Shortname");
        String longName = json.getString("Longname");
        String schoolName = json.getString("Schoolname");
        Course course = new Course(id, shortName, longName, schoolName);
        return course;
    }
}
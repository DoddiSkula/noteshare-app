package is.hi.noteshare.services;

import org.json.JSONException;
import org.json.JSONObject;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;

public interface DataService {
    File JsonToFile(JSONObject json) throws JSONException;
    User JsonToUser(String json);
    Course JsonToCourse(JSONObject json) throws JSONException;
}
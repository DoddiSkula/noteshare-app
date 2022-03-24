package is.hi.noteshare.services;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;

import org.json.JSONException;
import org.json.JSONObject;

public interface DataService {
    File JsonToFile(JSONObject json) throws JSONException;
    User JsonToUser(JSONObject json) throws JSONException;
    Course JsonToCourse(JSONObject json) throws JSONException;
}
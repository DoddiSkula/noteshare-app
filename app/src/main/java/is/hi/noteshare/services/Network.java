package is.hi.noteshare.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface Network {
    JSONArray getCourses() throws JSONException;
    JSONObject getCourse(long id) throws JSONException;
    JSONObject getUser() throws JSONException;
    JSONArray getFilesByCourse(long id) throws JSONException;
    JSONObject getFile(long id) throws JSONException;
}

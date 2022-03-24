package is.hi.noteshare.services;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;
import org.json.JSONObject;

public interface DataService {
    File JsonToFile(JSONObject json);
    User JsonToUser(JSONObject json);
    Course JsonToCourse(JSONObject json);
    JSONObject CourseToJson(Course course);
    JSONObject FileToJson(File file);
    JSONObject UserToJson(User user);
}
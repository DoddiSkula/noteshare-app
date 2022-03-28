package is.hi.noteshare.services;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;

public interface CoursesService {
    List<Course> getCourses() throws JSONException;
    List<Course> getCourses(ArrayList<Course> courses , String name);
}

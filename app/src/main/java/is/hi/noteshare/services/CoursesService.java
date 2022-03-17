package is.hi.noteshare.services;

import java.util.List;

import is.hi.noteshare.data.models.Course;

public interface CoursesService {
    List<Course> getCourses();
    List<Course> getCourses(String name);
}

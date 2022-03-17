package is.hi.noteshare.service;

import java.util.List;

import is.hi.noteshare.entities.Course;

public interface CoursesService {
    List<Course> getCourses();
    List<Course> getCourses(String name);
}

package is.hi.noteshare.services.implementation;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.services.CoursesService;


public class CoursesServiceImplementation implements CoursesService {

    public CoursesServiceImplementation() {
    }

    @Override
    public List<Course> getCourses(List<Course> courses, String name) {

        List<Course> searchList = new ArrayList<>();

        for (Course element : courses) {
            if (element.getLongName().toLowerCase().contains(name.toLowerCase())
                    || element.getShortName().toLowerCase().contains(name.toLowerCase())) {
                searchList.add(element);
            }
        }

        return searchList;
    }
}

package is.hi.noteshare.service.implementation;

import java.util.List;

import is.hi.noteshare.entities.Course;
import is.hi.noteshare.entities.File;
import is.hi.noteshare.service.CourseService;

public class CourseServiceImplementation implements CourseService {

    public CourseServiceImplementation() {}

    @Override
    public Course getCourse(long id) {
        return null;
    }

    @Override
    public List<File> getFilesByCourse(long id) {
        return null;
    }

    @Override
    public void openFile(File file) {

    }

    @Override
    public File getFile(long id) {
        return null;
    }

    @Override
    public void favourite(long userId, long courseId) {

    }

    @Override
    public void unFavourite(long userId, long courseId) {

    }
}

package is.hi.noteshare.service;

import java.util.List;

import is.hi.noteshare.entities.Course;
import is.hi.noteshare.entities.File;

public interface CourseService {
    Course getCourse(long id);
    List<File> getFilesByCourse(long id);
    void openFile(File file);
    File getFile(long id);
    void favourite(long userId, long courseId);
    void unFavourite(long userId, long courseId);
}

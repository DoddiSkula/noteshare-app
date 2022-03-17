package is.hi.noteshare.services;

import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;

public interface CourseService {
    Course getCourse(long id);
    List<File> getFilesByCourse(long id);
    void openFile(File file);
    File getFile(long id);
    void favourite(long userId, long courseId);
    void unFavourite(long userId, long courseId);
}

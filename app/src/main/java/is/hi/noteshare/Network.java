package is.hi.noteshare;


import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.User;

public class Network {

    public List<Course> getCourses() {
        // return list of dummy courses
        List<Course> mockCourses = new ArrayList<>();

        mockCourses.add(new Course(1 ,"HBV401G", "Þróun hugbúnaðar", "HÍ"));
        mockCourses.add(new Course(2, "HBV501G", "Hugbúnaðarverkefni 1", "HÍ"));
        mockCourses.add(new Course(3, "HBV601G", "Hugbúnaðarverkefni 2", "HÍ"));
        mockCourses.add(new Course(4, "TÖL101G", "Tölvunarfræði 1", "HÍ"));
        mockCourses.add(new Course(5, "TÖL107G", "Vefforritun 1", "HÍ"));
        mockCourses.add(new Course(6, "HBV201G", "Viðmótsforritun", "HÍ"));
        mockCourses.add(new Course(7, "TÖL104G", "Stærðfræðimynstur", "HÍ"));
        mockCourses.add(new Course(8, "TÖL203G", "Tölvunarfræði 2", "HÍ"));
        mockCourses.add(new Course(9, "TÖL401G", "Stýrikerfi", "HÍ"));
        mockCourses.add(new Course(10, "TÖL304G", "Forritunarmál", "HÍ"));

        return mockCourses;
    };

    public User getUser() {
        return new User(1, "admin", "admin@admin.com", "123");
    };
    
    /*
    getCourses(name: String);
    getCourse(id: long);
    getUser(id: long);
    getFilesByCourse(id: long);
    getFile(id: long);
    login(user: User);
    logout(user: User);
    signup(user: User);
    upload(file: File);
    favourite(userId: long, courseId: long);
    unFavourite(userId: long, courseId: long);*/
}

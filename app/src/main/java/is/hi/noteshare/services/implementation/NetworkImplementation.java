package is.hi.noteshare.services.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import is.hi.noteshare.services.Network;

public class NetworkImplementation implements Network {

    public NetworkImplementation() {}

    public JSONArray getCourses() throws JSONException {
        JSONArray courses = new JSONArray();
        JSONObject course = new JSONObject();

        course.put("id", 1);
        course.put("shortName", "HBV401G");
        course.put("longName", "Þróun hugbúnaðar");
        course.put("schoolName", "HÍ");
        courses.put(course);

        course.put("id", 2);
        course.put("shortName", "HBV501G");
        course.put("longName", "Hugbúnaðarverkefni 1");
        course.put("schoolName", "HÍ");
        courses.put(course);

        course.put("id", 3);
        course.put("shortName", "HBV601G");
        course.put("longName", "Hugbúnaðarverkefni 2");
        course.put("schoolName", "HÍ");
        courses.put(course);

        course.put("id", 4);
        course.put("shortName", "TÖL101G");
        course.put("longName", "Tölvunarfræði 1");
        course.put("schoolName", "HÍ");
        courses.put(course);

        course.put("id", 5);
        course.put("shortName", "TÖL107G");
        course.put("longName", "Vefforritun 1");
        course.put("schoolName", "HÍ");
        courses.put(course);

        course.put("id", 6);
        course.put("shortName", "HBV201G");
        course.put("longName", "Viðmótsforritun");
        course.put("schoolName", "HÍ");
        courses.put(course);

        return courses;
    };

    public JSONObject getCourse(long id) throws JSONException {
        JSONObject course = new JSONObject();
        course.put("id", 3);
        course.put("shortName", "HBV601G");
        course.put("longName", "Hugbúnaðarverkefni 2");
        course.put("schoolName", "HÍ");
        return course;
    }

    public JSONObject getUser() throws JSONException {
        JSONObject user = new JSONObject();
        user.put("id", 1);
        user.put("name", "Matthias Book");
        user.put("email", "admin@admin.com");
        user.put("password", "123");
        return user;
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

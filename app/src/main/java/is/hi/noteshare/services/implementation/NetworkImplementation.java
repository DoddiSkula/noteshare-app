package is.hi.noteshare.services.implementation;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import is.hi.noteshare.services.Network;

public class NetworkImplementation implements Network {

    public NetworkImplementation() {}

    public JSONArray getCourses() throws JSONException {
        JSONArray courses = new JSONArray();

        JSONObject course1 = new JSONObject();
        course1.put("id", 1);
        course1.put("shortName", "HBV401G");
        course1.put("longName", "Þróun hugbúnaðar");
        course1.put("schoolName", "HÍ");
        courses.put(course1);

        JSONObject course2 = new JSONObject();
        course2.put("id", 2);
        course2.put("shortName", "HBV501G");
        course2.put("longName", "Hugbúnaðarverkefni 1");
        course2.put("schoolName", "HÍ");
        courses.put(course2);

        JSONObject course3 = new JSONObject();
        course3.put("id", 3);
        course3.put("shortName", "HBV601G");
        course3.put("longName", "Hugbúnaðarverkefni 2");
        course3.put("schoolName", "HÍ");
        courses.put(course3);

        JSONObject course4 = new JSONObject();
        course4.put("id", 4);
        course4.put("shortName", "TÖL101G");
        course4.put("longName", "Tölvunarfræði 1");
        course4.put("schoolName", "HÍ");
        courses.put(course4);

        JSONObject course5 = new JSONObject();
        course5.put("id", 5);
        course5.put("shortName", "TÖL107G");
        course5.put("longName", "Vefforritun 1");
        course5.put("schoolName", "HÍ");
        courses.put(course5);

        JSONObject course6 = new JSONObject();
        course6.put("id", 6);
        course6.put("shortName", "HBV201G");
        course6.put("longName", "Viðmótsforritun");
        course6.put("schoolName", "HÍ");
        courses.put(course6);

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

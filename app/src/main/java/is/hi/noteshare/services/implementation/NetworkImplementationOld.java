package is.hi.noteshare.services.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import is.hi.noteshare.services.Network;

public class NetworkImplementationOld implements Network {

    public NetworkImplementationOld() {
    }

    public JSONArray getCourses() throws JSONException {
        JSONArray courses = new JSONArray();

        JSONObject course1 = new JSONObject();
        course1.put("id", 1);
        course1.put("shortName", "HBV401G");
        course1.put("longName", "Þróun hugbúnaðar");
        course1.put("schoolName", "Háskóli Íslands");
        courses.put(course1);

        JSONObject course2 = new JSONObject();
        course2.put("id", 2);
        course2.put("shortName", "HBV501G");
        course2.put("longName", "Hugbúnaðarverkefni 1");
        course2.put("schoolName", "Háskóli Íslands");
        courses.put(course2);

        JSONObject course3 = new JSONObject();
        course3.put("id", 3);
        course3.put("shortName", "HBV601G");
        course3.put("longName", "Hugbúnaðarverkefni 2");
        course3.put("schoolName", "Háskóli Íslands");
        courses.put(course3);

        JSONObject course4 = new JSONObject();
        course4.put("id", 4);
        course4.put("shortName", "TÖL101G");
        course4.put("longName", "Tölvunarfræði 1");
        course4.put("schoolName", "Háskóli Íslands");
        courses.put(course4);

        JSONObject course5 = new JSONObject();
        course5.put("id", 5);
        course5.put("shortName", "TÖL107G");
        course5.put("longName", "Vefforritun 1");
        course5.put("schoolName", "Háskóli Íslands");
        courses.put(course5);

        JSONObject course6 = new JSONObject();
        course6.put("id", 6);
        course6.put("shortName", "HBV201G");
        course6.put("longName", "Viðmótsforritun");
        course6.put("schoolName", "Háskóli Íslands");
        courses.put(course6);

        JSONObject course7 = new JSONObject();
        course7.put("id", 7);
        course7.put("shortName", "TÖL607G");
        course7.put("longName", "Keppnisforritun");
        course7.put("schoolName", "Háskóli Íslands");
        courses.put(course7);

        return courses;
    }

    ;

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
    }

    ;

    public JSONArray getFilesByCourse(long id) throws JSONException {
        JSONArray files = new JSONArray();

        JSONObject file1 = new JSONObject();
        file1.put("id", 1);
        file1.put("date", new Date());
        file1.put("title", "Rosa flott upplýsingar");
        file1.put("description", "Nú á DVD!");
        file1.put("type", ".pdf");
        files.put(file1);
        return files;
    }

    ;

    public JSONObject getFile(long id) throws JSONException {
        JSONObject file = new JSONObject();
        file.put("id", 1);
        file.put("date", new Date());
        file.put("title", "Rosa flott upplýsingar");
        file.put("description", "Nú á DVD!");
        file.put("type", ".pdf");
        return file;
    }

    ;
}

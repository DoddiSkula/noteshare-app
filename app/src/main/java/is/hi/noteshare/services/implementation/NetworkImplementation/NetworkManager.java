package is.hi.noteshare.services.implementation.NetworkImplementation;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.services.Network;


public class NetworkManager {

    private static final String BASE_URL = "https://noteshare-server.herokuapp.com/";

    private static NetworkManager mInstance;
    private static RequestQueue mQueue;
    private Context mContext;

    public static synchronized NetworkManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    private NetworkManager(Context context){
        mContext = context;
        mQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(mQueue == null){
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    String baseURL = "https://noteshare-server.herokuapp.com/";

    public void getCourses(NetworkCallback<List<Course>> callback) {
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "courses", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Course>>(){}.getType();
                List<Course> courses = gson.fromJson(response,listType);
                callback.onSuccess(courses);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }
        );
        mQueue.add(request);
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
    };

    public JSONObject getFile(long id) throws JSONException {
        JSONObject file = new JSONObject();
        file.put("id", 1);
        file.put("date", new Date());
        file.put("title", "Rosa flott upplýsingar");
        file.put("description", "Nú á DVD!");
        file.put("type", ".pdf");
        return file;
    };
    
    /*
    getCourses(name: String);
    getCourse(id: long);
    getUser(id: long);
    login(user: User);
    logout(user: User);
    signup(user: User);
    upload(file: File);
    favourite(userId: long, courseId: long);
    unFavourite(userId: long, courseId: long);*/
}

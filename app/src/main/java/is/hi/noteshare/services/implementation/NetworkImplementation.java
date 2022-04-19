package is.hi.noteshare.services.implementation;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import is.hi.noteshare.services.Network;
import is.hi.noteshare.services.NetworkCallback;


public class NetworkImplementation implements Network {

        private static final String BASE_URL = "https://noteshare-server.herokuapp.com";
        private final String TAG ="NetworkManager";
        private static NetworkImplementation mInstance;
        private static RequestQueue mQueue;
        private Context mContext;

    public NetworkImplementation() {

    }


    public static synchronized NetworkImplementation getInstance(Context context){
            if(mInstance == null) {
                mInstance = new NetworkImplementation(context);
            }
            return mInstance;
        }

        NetworkImplementation(Context context) {
            mContext = context;
            mQueue = getRequestQueue();
        }

        public RequestQueue getRequestQueue() {
            if(mQueue == null) {
                mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
            }
            return mQueue;
        }
        public byte[] getUrlBytes(String urlSpec) throws IOException {
            URL url = new URL(urlSpec);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            try {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    throw new IOException(conn.getResponseMessage() + ": with " + urlSpec);
                }
                int bytesRead = 0;
                byte[] buffer = new byte[1024];
                while ((bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                out.close();
                return out.toByteArray();
            } finally { conn.disconnect(); }
        }
        public String getUrlString(String urlSpec) throws IOException {
            return new String(getUrlBytes(urlSpec));
        }

        /**
         * Post request to the backend
         * @param url
         * @param requestBody
         * @param callback
         */
        public void post(String url, JSONObject requestBody, NetworkCallback<String> callback ){
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST, BASE_URL + url, requestBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess(response.toString());
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure(error.toString());
                }
            });

            mQueue.add(request);
        }
        /**
         * Get request to the backend
         * @param url
         * @param callback
         */
        public void get(String url, NetworkCallback<String> callback){
            StringRequest request = new StringRequest(
                    Request.Method.GET, BASE_URL + url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    callback.onSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFailure(error.toString());
                }
            }
            );
            mQueue.add(request);
        }

    @Override
    public JSONArray getCourses() throws JSONException {
        return null;
    }

    @Override
    public JSONObject getCourse(long id) throws JSONException {
        return null;
    }

    @Override
    public JSONObject getUser() throws JSONException {
        return null;
    }

    @Override
    public JSONArray getFilesByCourse(long id) throws JSONException {
        return null;
    }

    @Override
    public JSONObject getFile(long id) throws JSONException {
        return null;
    }
}
       /* JSONArray courses = new JSONArray();

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


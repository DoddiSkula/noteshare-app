package is.hi.noteshare.services.implementation.NetworkImplementation;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;


public class NetworkManager {

    private static final String BASE_URL = "https://noteshare-server.herokuapp.com/";

    private static NetworkManager mInstance;
    private static RequestQueue mQueue;
    private final Context mContext;

    public static synchronized NetworkManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkManager(context);
        }
        return mInstance;
    }

    private NetworkManager(Context context) {
        mContext = context;
        mQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    public void getCourses(NetworkCallback<List<Course>> callback) {
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "courses", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Course>>() {
                }.getType();
                List<Course> courses = gson.fromJson(response, listType);
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
    }

    public void getCourse(long id, NetworkCallback<Course> callback) {
        String strId = Long.toString(id);
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "course/" + strId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type Type = new TypeToken<Course>() {
                }.getType();
                Course course = gson.fromJson(response, Type);
                callback.onSuccess(course);
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

    public void getUser(String username, NetworkCallback<User> callback) {
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "user/" + username, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type Type = new TypeToken<User>() {
                }.getType();
                User user = gson.fromJson(response, Type);
                callback.onSuccess(user);
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

    public void getFilesByCourse(long id, NetworkCallback<List<File>> callback) {
        String strId = Long.toString(id);
        StringRequest request = new StringRequest(
                Request.Method.GET, BASE_URL + "course" + strId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<File>>() {
                }.getType();
                List<File> files = gson.fromJson(response, listType);
                callback.onSuccess(files);
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

    public void login(String username, String password, NetworkCallback<String> callback) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("username", username);
        jsonBody.put("password", password);
        final String requestBody = jsonBody.toString();

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };
        mQueue.add(request);
    }

    public void signUp(String email, String username, String password, NetworkCallback<String> callback) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("schoolId", 1);
        jsonBody.put("admin", false);
        jsonBody.put("email", email);
        jsonBody.put("username", username);
        jsonBody.put("password", password);
        final String requestBody = jsonBody.toString();

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "signup", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                return super.parseNetworkResponse(response);
            }
        };
        mQueue.add(request);
    }

    public void favourite(long userId, long courseId, NetworkCallback<String> callback) throws JSONException {
        String cId = Long.toString(courseId);
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("id", userId);
        final String requestBody = jsonBody.toString();

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "course/" + cId + "/favourite", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        mQueue.add(request);
    }

    public void unFavourite(long userId, long courseId, NetworkCallback<String> callback) throws JSONException {
        String cId = Long.toString(courseId);
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("id", userId);
        final String requestBody = jsonBody.toString();

        StringRequest request = new StringRequest(Request.Method.POST, BASE_URL + "course/" + cId + "/remove", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        mQueue.add(request);
    }

    /*
    logout(user: User); Engin þörf
    upload(file: File); Bíða
     */
}

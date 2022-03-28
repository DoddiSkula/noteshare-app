package is.hi.noteshare.services.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.services.CourseService;
import is.hi.noteshare.services.DataService;
import is.hi.noteshare.services.Network;

public class CourseServiceImplementation implements CourseService {

    private final Network mNetwork;
    private final DataService mDataService;

    public CourseServiceImplementation() {
        this.mNetwork = new NetworkImplementation();
        this.mDataService = new DataServiceImplementation();
    }

    @Override
    public Course getCourse(long id) {
        Course course = null;
        try {
            JSONObject json = mNetwork.getCourse(id);
            course = mDataService.JsonToCourse(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public List<File> getFilesByCourse(long id) {
        JSONArray newJsonArr = new JSONArray();
        ArrayList<File> newJavaArr = new ArrayList<File>();

        try {
            newJsonArr = mNetwork.getFilesByCourse(id);
            for(int i = 0; i < newJsonArr.length(); i++){
                newJavaArr.add(mDataService.JsonToFile((JSONObject) newJsonArr.get(i)));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return newJavaArr;
    }

    @Override
    public void openFile(File file) {

    }

    @Override
    public File getFile(long id) {
        File file = null;
        try {
            JSONObject json = mNetwork.getFile(id);
            file = mDataService.JsonToFile(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void favourite(long userId, long courseId) {

    }

    @Override
    public void unFavourite(long userId, long courseId) {

    }
}

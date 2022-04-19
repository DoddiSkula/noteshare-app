package is.hi.noteshare.services.implementation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.services.CoursesService;
import is.hi.noteshare.services.DataService;
import is.hi.noteshare.services.Network;



public class CoursesServiceImplementation implements CoursesService {

    private final Network mNetwork;
    private final DataService mDataService;

    public CoursesServiceImplementation() {
        this.mNetwork = new NetworkImplementationOld();
        this.mDataService = new DataServiceImplementation();
    }
    @Override
    public List<Course> getCourses() throws JSONException {

        JSONArray newJsonArr = new JSONArray();
        ArrayList<Course> newJavaArr = new ArrayList<Course>();

        try {
            newJsonArr = mNetwork.getCourses();
            for(int i = 0; i < newJsonArr.length(); i++){
                newJavaArr.add(mDataService.JsonToCourse((JSONObject) newJsonArr.get(i)));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

        return newJavaArr;
    }

    @Override
    public List<Course> getCourses(ArrayList<Course> courses, String name) {

        ArrayList<Course> searchList = new ArrayList<Course>();

        for(Course element : courses){
            if(element.getLongName().contains(name) || element.getShortName().contains(name)){
                searchList.add(element);
            }
        }
        
        return searchList;
    }
}

package is.hi.noteshare.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.services.CoursesService;
import is.hi.noteshare.services.implementation.CoursesServiceImplementation;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.databinding.FragmentCoursesBinding;

public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;
    private List<Course> mCourses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CoursesService coursesService = new CoursesServiceImplementation();

        RecyclerView recyclerView = binding.courseList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        try {
            mCourses = coursesService.getCourses();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CourseAdapter adapter = new CourseAdapter(this.getActivity(), mCourses);
        recyclerView.setAdapter(adapter);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
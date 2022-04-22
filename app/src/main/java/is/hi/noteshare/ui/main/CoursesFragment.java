package is.hi.noteshare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.databinding.FragmentCoursesBinding;
import is.hi.noteshare.services.CoursesService;
import is.hi.noteshare.services.implementation.CoursesServiceImplementation;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkCallback;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkManager;
import is.hi.noteshare.ui.adapters.CourseAdapter;
import is.hi.noteshare.ui.course.CourseActivity;

public class CoursesFragment extends Fragment implements CourseAdapter.onCourseListener {

    private FragmentCoursesBinding binding;
    private CoursesService mCoursesService;
    private CourseAdapter mCourseAdapter;
    private NetworkManager mNetworkManager;
    private List<Course> mCourses;
    private List<Course> mFilteredCourses;


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

        // Initialize services
        mCoursesService = new CoursesServiceImplementation();
        mNetworkManager = NetworkManager.getInstance(this.getActivity());

        // Extract UI elements
        RecyclerView recyclerView = binding.courseList;
        EditText searchBar = binding.searchBar;


        mNetworkManager.getCourses(new NetworkCallback<List<Course>>() {
            @Override
            public void onSuccess(List<Course> courses) {
                mCourses = courses;
                mFilteredCourses = mCourses;
                mCourseAdapter = new CourseAdapter(CoursesFragment.this.getActivity(), mCourses, CoursesFragment.this);
                recyclerView.setAdapter(mCourseAdapter);
            }

            @Override
            public void onFailure(String errorString) {
                Log.e("Get Courses", errorString);
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mFilteredCourses = mCoursesService.getCourses(mCourses, charSequence.toString());
                mCourseAdapter.setCourses(mFilteredCourses);
                recyclerView.setAdapter(mCourseAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Initialize UI
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(this.getActivity(), CourseActivity.class);
        intent.putExtra("Course", mFilteredCourses.get(position).getLongName());
        intent.putExtra("CourseId", mFilteredCourses.get(position).getId());
        startActivity(intent);
    }
}
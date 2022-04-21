package is.hi.noteshare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.databinding.FragmentProfileBinding;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.UserServiceImplementation;
import is.hi.noteshare.ui.adapters.CourseAdapter;
import is.hi.noteshare.ui.course.CourseActivity;
import is.hi.noteshare.ui.login.LoginActivity;

public class ProfileFragment extends Fragment implements CourseAdapter.onCourseListener {

    private FragmentProfileBinding binding;
    private UserService mUserService;
    private CourseAdapter mCourseAdapter;
    private List<Course> mCourses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize services
        mUserService = new UserServiceImplementation(requireActivity());

        // Extract UI elements
        TextView profileName = (TextView) binding.profileName;
        TextView profileEmail = (TextView) binding.profileEmail;
        TextView signout = (TextView) binding.signout;
        RecyclerView recyclerView = (RecyclerView) binding.myCoursesList;

        // Get logged in user
        User user = mUserService.getStoredUser();
        mCourses = user.getCourses();

        // Initialize UI
        profileName.setText(user.getUsername());
        profileEmail.setText(user.getEmail());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // Populate My Courses list
        if (mCourses == null) mCourses = new ArrayList<>();
        mCourseAdapter = new CourseAdapter(ProfileFragment.this.getActivity(), mCourses, this);
        recyclerView.setAdapter(mCourseAdapter);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserService.storeUser(new User());
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
        intent.putExtra("Course", mCourses.get(position).getLongName());
        startActivity(intent);
    }
}
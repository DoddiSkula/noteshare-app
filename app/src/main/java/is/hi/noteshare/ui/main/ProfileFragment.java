package is.hi.noteshare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import is.hi.noteshare.data.models.File;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.databinding.FragmentProfileBinding;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkCallback;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkManager;
import is.hi.noteshare.services.implementation.UserServiceImplementation;
import is.hi.noteshare.ui.adapters.CourseAdapter;
import is.hi.noteshare.ui.adapters.FileAdapter;
import is.hi.noteshare.ui.course.CourseActivity;
import is.hi.noteshare.ui.login.LoginActivity;

public class ProfileFragment extends Fragment implements FileAdapter.onFileListener, CourseAdapter.onCourseListener {

    private FragmentProfileBinding binding;
    private UserService mUserService;
    private NetworkManager mNetworkManager;
    private CourseAdapter mCourseAdapter;
    private FileAdapter mFileAdapter;
    private List<Course> mCourses;
    private List<File> mFiles;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerViewFiles;

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
        mNetworkManager = NetworkManager.getInstance(this.getActivity());

        // Extract UI elements
        TextView profileName = (TextView) binding.profileName;
        TextView profileEmail = (TextView) binding.profileEmail;
        TextView signout = (TextView) binding.signout;
        mRecyclerView = (RecyclerView) binding.myCoursesList;
        mRecyclerViewFiles = (RecyclerView) binding.userFilesList;

        // Get logged in user
        User user = mUserService.getStoredUser();
        mCourses = user.getCourses();

        // Initialize UI
        profileName.setText(user.getUsername());
        profileEmail.setText(user.getEmail());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerViewFiles.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        // Populate My Courses list
        if (mCourses == null) mCourses = new ArrayList<>();
        mCourseAdapter = new CourseAdapter(ProfileFragment.this.getActivity(), mCourses, this);
        mRecyclerView.setAdapter(mCourseAdapter);

        // Populate My Uploads list
        updateFilesList(user.getId());

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

    private void updateFilesList(long userId) {
        mNetworkManager.getFilesByUser(userId, new NetworkCallback<List<File>>() {
            @Override
            public void onSuccess(List<File> files) {
                mFiles = files;
                mFileAdapter = new FileAdapter(ProfileFragment.this.getActivity(), mFiles, ProfileFragment.this);
                mRecyclerViewFiles.setAdapter(mFileAdapter);
            }
            @Override
            public void onFailure(String errorString) {
                Log.e("Get Files", errorString);
            }
        });
    }

    @Override
    public void onResume() {
        User user = mUserService.getStoredUser();

        mCourses = user.getCourses();
        if (mCourses == null) mCourses = new ArrayList<>();

        mCourseAdapter = new CourseAdapter(ProfileFragment.this.getActivity(), mCourses, this);
        mRecyclerView.setAdapter(mCourseAdapter);

        updateFilesList(user.getId());

        super.onResume();
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
        intent.putExtra("CourseId", mCourses.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onFileClick(int position) {

    }
}
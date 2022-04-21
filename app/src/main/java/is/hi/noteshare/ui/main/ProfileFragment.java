package is.hi.noteshare.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import is.hi.noteshare.data.models.User;
import is.hi.noteshare.databinding.FragmentProfileBinding;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.UserServiceImplementation;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private UserService mUserService;

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

        // Get logged in user
        User user = mUserService.getStoredUser();

        // Initialize UI
        profileName.setText(user.getUsername());
        profileEmail.setText(user.getEmail());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
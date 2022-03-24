package is.hi.noteshare.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.Objects;

import is.hi.noteshare.databinding.FragmentProfileBinding;
import is.hi.noteshare.ui.login.LoginActivity;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences sharedPref = requireActivity().getSharedPreferences("NoteShare", Context.MODE_PRIVATE);

        String name = sharedPref.getString("name", "");
        String email = sharedPref.getString("email", "");

        TextView profileName = (TextView) binding.profileName;
        TextView profileEmail = (TextView) binding.profileEmail;

        profileName.setText(name);
        profileEmail.setText(email);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package is.hi.noteshare.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import is.hi.noteshare.R;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.UserServiceImplementation;
import is.hi.noteshare.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserService = new UserServiceImplementation();

        EditText emailInput = (EditText) findViewById(R.id.editEmail);
        EditText passwordInput = (EditText) findViewById(R.id.editPassword);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                User user = mUserService.login(email, password);

                if (user != null) {
                    SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("NoteShare", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putLong("id", user.getId());
                    editor.putString("email", user.getEmail());
                    editor.putString("name", user.getName());
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

}
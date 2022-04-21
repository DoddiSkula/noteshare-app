package is.hi.noteshare.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import is.hi.noteshare.R;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.DataService;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.DataServiceImplementation;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkCallback;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkManager;
import is.hi.noteshare.services.implementation.UserServiceImplementation;
import is.hi.noteshare.ui.main.MainActivity;
import is.hi.noteshare.ui.signup.SignupActivity;

public class LoginActivity extends AppCompatActivity {
    private UserService mUserService;
    private DataService mDataService;
    private NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize services
        mUserService = new UserServiceImplementation(LoginActivity.this);
        mDataService = new DataServiceImplementation();
        mNetworkManager = NetworkManager.getInstance(LoginActivity.this);

        // Extract UI elements
        EditText usernameInput = (EditText) findViewById(R.id.editUsername);
        EditText passwordInput = (EditText) findViewById(R.id.editPassword);
        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        Button signupButton = (Button) findViewById(R.id.buttonSignupFromLogin);

        // Initialize listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                try {
                    mNetworkManager.login(username, password, new NetworkCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            User user = mDataService.JsonToUser(result);
                            mUserService.storeUser(user);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(String errorString) {
                            Log.e("Login Response", errorString);
                            Toast toast = Toast.makeText(getApplicationContext(), "Username or Password incorrect", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

}
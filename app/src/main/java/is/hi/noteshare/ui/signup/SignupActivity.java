package is.hi.noteshare.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import is.hi.noteshare.ui.login.LoginActivity;
import is.hi.noteshare.ui.main.MainActivity;

public class SignupActivity extends AppCompatActivity {

    private NetworkManager mNetworkManager;
    private DataService mDataService;
    private UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize services
        mNetworkManager = NetworkManager.getInstance(SignupActivity.this);
        mDataService = new DataServiceImplementation();
        mUserService = new UserServiceImplementation(SignupActivity.this);

        // Extract UI elements
        Button login = (Button) findViewById(R.id.buttonLoginFromSignup);
        Button signup = (Button) findViewById(R.id.buttonSignup);
        TextView usernameInput = (TextView) findViewById(R.id.editUsernameSignup);
        TextView emailInput = (TextView) findViewById(R.id.editEmailSignup);
        TextView passwordInput = (TextView) findViewById(R.id.editPasswordSignup);

        // Initialize listeners
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                try {
                    mNetworkManager.signUp(email, username, password, new NetworkCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            User user = mDataService.JsonToUser(result);
                            mUserService.storeUser(user);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(String errorString) {
                            Log.e("Signup Response", errorString);
                            Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
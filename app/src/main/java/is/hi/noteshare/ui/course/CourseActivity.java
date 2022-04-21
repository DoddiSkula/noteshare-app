package is.hi.noteshare.ui.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.util.List;

import is.hi.noteshare.R;
import is.hi.noteshare.data.models.Course;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkCallback;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkManager;
import is.hi.noteshare.services.implementation.UserServiceImplementation;
import is.hi.noteshare.ui.upload.UploadActivity;

public class CourseActivity extends AppCompatActivity {

    private NetworkManager mNetworkManager;
    private UserService mUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Initialize services
        mNetworkManager = NetworkManager.getInstance(CourseActivity.this);
        mUserService = new UserServiceImplementation(CourseActivity.this);

        // Extract UI elements
        TextView coursetitle = (TextView) findViewById(R.id.courseTitle);
        Button favouriteButton = (Button) findViewById(R.id.favouriteButton);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingAddButton);

        // Get course info
        Intent intent = getIntent();
        String courseName = intent.getStringExtra("Course");
        long courseId = intent.getLongExtra("CourseId", 0);

        // Get logged in user
        User user = mUserService.getStoredUser();
        long userId = user.getId();

        // Initialize UI
        setFavouriteButton(user, courseId, favouriteButton);
        coursetitle.setText(courseName);

        // Initialize listeners
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favouriteButton.getText().toString().equals("favourite")) {
                    try {
                        mNetworkManager.favourite(userId, courseId, new NetworkCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                favouriteButton.setText("remove favourite");
                                favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24, 0, 0, 0);
                                Toast toast = Toast.makeText(getApplicationContext(), "Course added to My Courses", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            @Override
                            public void onFailure(String errorString) {
                                Log.e("Favourite Response", errorString);
                                Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return;
                }

                if (favouriteButton.getText().toString().equals("remove favourite")) {
                    try {
                        mNetworkManager.unFavourite(userId, courseId, new NetworkCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                favouriteButton.setText("favourite");
                                favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_border_24, 0, 0, 0);
                                Toast toast = Toast.makeText(getApplicationContext(), "Course removed from My Courses", Toast.LENGTH_SHORT);
                                toast.show();
                            }

                            @Override
                            public void onFailure(String errorString) {
                                Log.e("Unfavourite Response", errorString);
                                Toast toast = Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setFavouriteButton(User user, long courseId, Button favouriteButton) {
        List<Course> userCourses = user.getCourses();

        for (Course c : userCourses) {
            if (c.getId() == courseId) {
                favouriteButton.setText("remove favourite");
                favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24, 0, 0, 0);
                return;
            }
        }

        favouriteButton.setText("favourite");
        favouriteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_favorite_border_24, 0, 0, 0);
    }
}
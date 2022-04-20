package is.hi.noteshare.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import is.hi.noteshare.R;
import is.hi.noteshare.ui.login.LoginActivity;
import is.hi.noteshare.ui.main.MainActivity;
import is.hi.noteshare.ui.upload.UploadActivity;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingAddButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }
}
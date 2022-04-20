package is.hi.noteshare.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import is.hi.noteshare.R;
import is.hi.noteshare.ui.upload.UploadActivity;
import is.hi.noteshare.services.CourseService;
import is.hi.noteshare.services.implementation.CourseServiceImplementation;

public class CourseActivity extends AppCompatActivity {

    private CourseService mCourseService;


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

        Intent intent = getIntent();
        mCourseService = new CourseServiceImplementation();

        TextView coursetitle = (TextView) findViewById(R.id.courseTitle);

        String coursename = intent.getStringExtra("Course");
        coursetitle.setText(coursename);

    }
}
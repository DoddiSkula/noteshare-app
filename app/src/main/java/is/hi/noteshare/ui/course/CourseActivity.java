package is.hi.noteshare.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import is.hi.noteshare.R;
import is.hi.noteshare.services.CourseService;
import is.hi.noteshare.services.implementation.CourseServiceImplementation;

public class CourseActivity extends AppCompatActivity {

    private CourseService mCourseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Intent intent = getIntent();
        mCourseService = new CourseServiceImplementation();

        TextView coursetitle = (TextView) findViewById(R.id.courseTitle);

        String coursename = intent.getStringExtra("Course");
        coursetitle.setText(coursename);
    }
}
package is.hi.noteshare.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import is.hi.noteshare.R;
import is.hi.noteshare.data.models.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {

    private final List<Course> mCourses;
    private final Context mContext;

    public CourseAdapter(Context context, List<Course> courses) {
        mContext = context;
        mCourses = courses;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_card, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.setDetails(course);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public static class CourseHolder extends RecyclerView.ViewHolder {

        private final TextView shortName;
        private final TextView longName;
        private final TextView schoolName;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            shortName = itemView.findViewById(R.id.courseShortName);
            longName =  itemView.findViewById(R.id.courseLongName);
            schoolName =  itemView.findViewById(R.id.courseSchoolName);
        }

        void setDetails(Course course) {
            shortName.setText(course.getShortName());
            longName.setText(course.getLongName());
        }
    }
}

package is.hi.noteshare.ui.adapters;

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

    private List<Course> mCourses;
    private final Context mContext;
    private final onCourseListener mOnCourseListener;

    public CourseAdapter(Context context, List<Course> courses, onCourseListener onCourseListener) {
        mContext = context;
        mCourses = courses;
        mOnCourseListener = onCourseListener;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_card, parent, false);
        return new CourseHolder(view, mOnCourseListener);
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

    public static class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView shortName;
        private final TextView longName;
        private final TextView schoolName;
        onCourseListener mOnCourseListener;

        public CourseHolder(@NonNull View itemView, onCourseListener onCourseListener) {
            super(itemView);
            shortName = itemView.findViewById(R.id.courseShortName);
            longName = itemView.findViewById(R.id.courseLongName);
            schoolName = itemView.findViewById(R.id.courseSchoolName);
            this.mOnCourseListener = onCourseListener;
            itemView.setOnClickListener(this);
        }

        void setDetails(Course course) {
            shortName.setText(course.getShortName());
            longName.setText(course.getLongName());
        }

        @Override
        public void onClick(View view) {
            mOnCourseListener.onCourseClick(getAdapterPosition());
        }
    }

    public interface onCourseListener {
        void onCourseClick(int position);
    }
}

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

import is.hi.noteshare.data.models.File;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileHolder> {

    private List<File> mFiles;
    private final Context mContext;
    private final onFileListener mOnFileListener;

    public FileAdapter(Context context, List<File> files, onFileListener onFileListener) {
        mContext = context;
        mFiles = files;
        mOnFileListener = onFileListener;
    }

    public void setFiles(List<File> files) {
        mFiles = files;
    }

    @NonNull
    @Override
    public FileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_card, parent, false);
        return new FileHolder(view, mOnFileListener);
    }


    @Override
    public void onBindViewHolder(@NonNull FileAdapter.FileHolder holder, int position) {
        File file = mFiles.get(position);
        holder.setDetails(file);
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public static class FileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView fileName;
        private final TextView uploaderName;

        onFileListener mOnFileListener;

        public FileHolder(@NonNull View itemView, onFileListener onFileListener) {
            super(itemView);
            fileName = itemView.findViewById(R.id.fileName);
            uploaderName = itemView.findViewById(R.id.uploaderName);

            this.mOnFileListener = onFileListener;
            itemView.setOnClickListener(this);
        }

        void setDetails(File file) {
            fileName.setText(file.getTitle());
            uploaderName.setText(file.getDescription());
        }

        @Override
        public void onClick(View view) {
            mOnFileListener.onFileClick(getAdapterPosition());
        }
    }

    public interface onFileListener {
        void onFileClick(int position);
    }
}

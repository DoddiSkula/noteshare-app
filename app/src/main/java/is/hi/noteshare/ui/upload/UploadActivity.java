package is.hi.noteshare.ui.upload;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import is.hi.noteshare.R;
import is.hi.noteshare.data.models.User;
import is.hi.noteshare.services.UserService;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkCallback;
import is.hi.noteshare.services.implementation.NetworkImplementation.NetworkManager;
import is.hi.noteshare.services.implementation.UserServiceImplementation;

public class UploadActivity extends AppCompatActivity {

    private TextView uploadFileText;
    private NetworkManager mNetworkManager;
    private UserService mUserService;

    private int REQ_PDF = 21;
    private String encodedPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Initialize services
        mNetworkManager = NetworkManager.getInstance(UploadActivity.this);
        mUserService = new UserServiceImplementation(UploadActivity.this);

        // Extract UI elements
        EditText titleInput = (EditText) findViewById(R.id.uploadFileName);
        EditText descriptionInput = (EditText) findViewById(R.id.uploadFileDescription);
        Button selectButton = findViewById(R.id.selectFileButton);
        Button uploadButton = findViewById(R.id.uploadButton);
        uploadFileText = findViewById(R.id.uploadFileText);

        // Get course info
        Intent intent = getIntent();
        long courseId = intent.getLongExtra("CourseId", 0);

        // Get logged in user
        User user = mUserService.getStoredUser();
        long userId = user.getId();

        // Initialize Activity Result Launcher
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();

                            try {
                                InputStream inputStream = UploadActivity.this.getContentResolver().openInputStream(uri);
                                byte[] pdfInBytes = new byte[inputStream.available()];
                                inputStream.read(pdfInBytes);
                                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.URL_SAFE);

                                String filename = getPDFPath(uri);
                                uploadFileText.setText(filename);

                                for (byte b : pdfInBytes) {
                                    Log.i("myactivity", String.format("0x%20x", b));
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        // Initialize listeners
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                Date today = new Date();
                byte[] emptyData = new byte[0];

                try {
                    mNetworkManager.uploadFile(userId, courseId, today, title, description, "file.pdf", 0, emptyData, new NetworkCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast toast = Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        @Override
                        public void onFailure(String errorString) {
                            Log.e("Upload Response", errorString);
                            Toast toast = Toast.makeText(getApplicationContext(), "Upload failed", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                someActivityResultLauncher.launch(chooseFile);
            }
        });

    }

    public String getPDFPath(Uri uri) {
        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
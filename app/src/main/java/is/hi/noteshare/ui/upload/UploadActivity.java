package is.hi.noteshare.ui.upload;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import is.hi.noteshare.R;

public class UploadActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        // Initialize result launcher
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Initialize result data
                        Intent data = result.getData();
                        // check condition
                        if (data != null) {
                            // Get PDf uri
                            Uri sUri = data.getData();
                            // Get PDF path
                            String sPath = sUri.getPath();

                            Log.e("data", String.valueOf(data));
                            Log.e("sUri", String.valueOf(sUri.getLastPathSegment()));
                            Log.e("sPath", String.valueOf(sPath));
                            File file = new File(sUri.getPath());
                            Log.e("file name", file.getName());
                        }
                    }
                });

        Button selectButton = (Button) findViewById(R.id.selectFileButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                resultLauncher.launch(intent);
            }
        });

        Button uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
package is.hi.noteshare.ui.upload;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import is.hi.noteshare.R;
import is.hi.noteshare.ui.main.MainActivity;

public class UploadActivity extends AppCompatActivity {

    private Button selectButton, uploadButton;
    private TextView uploadFileText;
    private EditText uploadFileDescription, uploadFileName;

    private int REQ_PDF = 21;
    private String encodedPDF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        selectButton = findViewById(R.id.selectFileButton);
        uploadButton = findViewById(R.id.uploadButton);
        uploadFileText = findViewById(R.id.uploadFileText);



        uploadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                uploadDocument();
            }
        });

        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri uri = data.getData();

                            int tjekk = 0;
                            try {
                                InputStream inputStream = UploadActivity.this.getContentResolver().openInputStream(uri);
                                byte[] pdfInBytes = new byte[inputStream.available()];
                                inputStream.read(pdfInBytes);
                                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.URL_SAFE);

                                String filename = getPDFPath(uri);
                                uploadFileText.setText(filename);
                                
                                for (byte b: pdfInBytes){
                                    Log.i("myactivity", String.format("0x%20x", b));
                                }
                                Log.e("Pdfinbytes", pdfInBytes.toString() );
                                Log.e("encodedPdf", encodedPDF);
                                Log.e("tjekk", String.valueOf(tjekk));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );


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

    private void uploadDocument() {


    }


}
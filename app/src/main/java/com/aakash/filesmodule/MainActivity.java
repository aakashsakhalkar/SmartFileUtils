package com.aakash.filesmodule;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aakash.java_android_file_utils.FileUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        File file = new File(getFilesDir(), "sample.pdf");

        Log.d(TAG, "formatFileSize: " + FileUtils.formatFileSize(1234567));
        Log.d(TAG, "getFileSizeInKB: " + FileUtils.getFileSizeInKB(file));
        Log.d(TAG, "getFileSizeInMB: " + FileUtils.getFileSizeInMB(file));

        String testFileName = "example_document.pdf";

        Log.d(TAG, "getFileExtension: " + FileUtils.getFileExtension(testFileName));
        Log.d(TAG, "getFileNameWithoutExtension: " + FileUtils.getFileNameWithoutExtension(testFileName));
        Log.d(TAG, "isImageFile: " + FileUtils.isImageFile(testFileName));
        Log.d(TAG, "isVideoFile: " + FileUtils.isVideoFile("movie.mkv"));
        Log.d(TAG, "isAudioFile: " + FileUtils.isAudioFile("music.mp3"));
        Log.d(TAG, "isPdfFile: " + FileUtils.isPdfFile(testFileName));
        Log.d(TAG, "isDocumentFile: " + FileUtils.isDocumentFile(testFileName));

        Log.d(TAG, "getMimeTypeFromExtension: " + FileUtils.getMimeTypeFromExtension("pdf"));
        Log.d(TAG, "getMimeTypeFromFile: " + FileUtils.getMimeTypeFromFile(file));
        Log.d(TAG, "isMimeType: " + FileUtils.isMimeType(file, "application/"));

        Log.d(TAG, "deleteFileIfExists: " + FileUtils.deleteFileIfExists(new File(getFilesDir(), "delete_me.txt")));
        Log.d(TAG, "isValidFile: " + FileUtils.isValidFile(file));
        Log.d(TAG, "getLastModifiedFormatted: " + FileUtils.getLastModifiedFormatted(file));

        Uri testUri = Uri.parse("content://com.example.provider/document/example.pdf");
        Log.d(TAG, "getReadableFileNameFromUri: " + FileUtils.getReadableFileNameFromUri(this, testUri));

        Log.d(TAG, "getParentFolderName: " + FileUtils.getParentFolderName(file));
        Log.d(TAG, "renameFile: " + FileUtils.renameFile(file, "renamed_sample.pdf"));

        File src = new File(getFilesDir(), "source.txt");
        File dest = new File(getFilesDir(), "copied.txt");
        Log.d(TAG, "copyFile: " + FileUtils.copyFile(src, dest));

        Log.d(TAG, "isExternalStorageWritable: " + FileUtils.isExternalStorageWritable());

        Log.d(TAG, "guessTypeFromMime: " + FileUtils.guessTypeFromMime("application/pdf"));
        Log.d(TAG, "getFileAgeDescription: " + FileUtils.getFileAgeDescription(file));
    }
}
package com.course.udacity.android.worldofat.Activity;

import android.os.Bundle;

import com.course.udacity.android.worldofat.R;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class FileSharingActivity extends AppCompatActivity {

    // The path to the root of this app's storage
    private File  mPrivateRootDir;
    // The path to the images subdirectory
    private File mImageDir;
    // Array of files in the image subdir
    File[] mImageFiles;
    //Array of filenames corresponding to imageFiles
    String[] mImageFilenames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_sharing);
    }

}


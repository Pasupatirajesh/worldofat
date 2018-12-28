package com.course.udacity.android.worldofat;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;

public class FileSharingActivity extends Activity {

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


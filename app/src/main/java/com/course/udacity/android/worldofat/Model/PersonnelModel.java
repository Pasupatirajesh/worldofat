package com.course.udacity.android.worldofat.Model;

import android.content.Context;
import android.os.Build;

import java.io.IOException;

import androidx.annotation.RequiresApi;

/**
 * Created by SSubra27 on 4/3/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PersonnelModel {

    private Context mContext;




    public PersonnelModel(Context context) throws IOException {
        this.mContext = context;
    }

<<<<<<< HEAD
    private String[] name = new String[] {"GH", "KH", "PP", "FL",
    "KW", "SS", "BS"};

    private String[] email = new String[]{"gh@example.com", "ho@example.com", "po@example.com",
=======
    private String[] name = new String[] {"Glenn Hedman", "Kathy Hooyenga", "Pat Politano", "Fani Lee",
    "Kathy Waldera", "Sathya Subramanian", "Brenda Sposato"};

    private String[] email = new String[]{"ghedman@uic.edu", "hooyenga@uic.edu", "politano@uic.edu",
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586
    "lobster@example.com", "dai@example.com", "kova@example.com", "radish@example.com"};
    private String[] image = new String[]{};

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String[] getName() {
        return name;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }
}

<<<<<<< HEAD
=======

//    public PersonnelModel(Context context, String[] name, String[] email, String[] image) throws IOException {
//        this.mContext = context;
//        this.mName = name;
//        this.mEmail = email;
//        this.mImage = image;
//    }
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586

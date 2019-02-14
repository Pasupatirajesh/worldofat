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

    private String[] name = new String[] {"GH", "KH", "PP", "FL",
    "KW", "SS", "BS"};

    private String[] email = new String[]{"gh@example.com", "ho@example.com", "po@example.com",

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



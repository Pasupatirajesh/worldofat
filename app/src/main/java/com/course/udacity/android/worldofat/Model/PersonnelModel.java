package com.course.udacity.android.worldofat.Model;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;

/**
 * Created by SSubra27 on 4/3/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class PersonnelModel {

    private Context mContext;




    public PersonnelModel(Context context) throws IOException {
        this.mContext = context;
    }

    private String[] name = new String[] {"Glenn Hedman", "Kathy Hooyenga",
            "Pat Politano","Fani Lee", "Kathy Waldera","Sathya Subramanian",
            "Brenda Sposato", "James Graham"};

    private String[] email = new String[]{"ghedman@uic.edu","hooyenga@uic.edu",
            "politano@uic.edu", "flee9@uic.edu","kwalde3@uic.edu", "ssubra27@uic.edu",
            "sposato@uic.edu",  "jlgraham@uic.edu"};
    private String[] image = mContext != null ? mContext.getAssets().list("pictures") : new String[0];

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


//    public PersonnelModel(Context context, String[] name, String[] email, String[] image) throws IOException {
//        this.mContext = context;
//        this.mName = name;
//        this.mEmail = email;
//        this.mImage = image;
//    }
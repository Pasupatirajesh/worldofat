package com.course.udacity.android.worldofat;

import com.infoedge.jrandomizer.providers.Provider;

public class CustomJobObjectProvider extends Provider<String[]> {

    private CustomJobObjectProvider(Class<String[]> clazz) {
        super(clazz);
    }

    @Override
    public String[] provide() {
        if(mLoadedData == null){
            mLoadedData = new String[]{
                    "Occupational Therapist", "Rehabilitation Engineer",
                    "Physical Therapist", "Speech Language Pathologist", "Architect", "Shop Assistant",
                    "Office Administrator"
            };
        }

        return mLoadedData;
    }


}

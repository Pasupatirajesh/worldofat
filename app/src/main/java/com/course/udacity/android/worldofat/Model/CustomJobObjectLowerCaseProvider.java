package com.course.udacity.android.worldofat;

import com.infoedge.jrandomizer.providers.Provider;

class CustomJobObjectLowerCaseProvider extends Provider<String[]> {

    private CustomJobObjectLowerCaseProvider(Class<String[]> clazz) {
        super(clazz);
    }


    @Override
    public String[] provide() {
        if(mLoadedData == null){
            mLoadedData = new String[]{
                    "occupational therapist", "rehabilitation engineer",
                    "physical therapist", "speech language pathologist", "architect", "shop assistant",
                    "office administrator"
            };
        }

        return mLoadedData;
    }
}

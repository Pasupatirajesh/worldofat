package com.course.udacity.android.worldofat;

public class CustomJobLocationProvider extends com.infoedge.jrandomizer.providers.Provider<String[]>{

    private CustomJobLocationProvider(Class<String[]> clazz){
        super(clazz);

    }

    @Override
    public String[] provide() {
        if(mLoadedData == null){
            mLoadedData = new String[]{

                    "ILLINOIS", "NEW YORK",
                    "NEW JERSEY", "RHODE ISLAND", "NEW HAMPSHIRE",
                    "VERMONT", "CONNECTICUT"
            };
        }
        return mLoadedData;
    }


}

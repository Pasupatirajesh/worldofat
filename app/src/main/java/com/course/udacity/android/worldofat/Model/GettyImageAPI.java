package com.course.udacity.android.worldofat.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by SSubra27 on 3/13/18.
 */

public interface GettyImageAPI  {

    @GET("images?phrase=Assistive Technology")
    Call<GettyImageModel> loadImages(@Header ("Api-Key") String apikey);
}




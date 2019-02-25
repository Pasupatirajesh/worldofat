package com.course.udacity.android.worldofat.Networking;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Model.GettyImageAPI;
import com.course.udacity.android.worldofat.Model.GettyImageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by SSubra27 on 3/13/18.
 */

public class Controller  {

    private static final String TAG = Controller.class.getSimpleName();
    private Context mContext;
    private GettyImageModel gettyImageModel = new GettyImageModel();
    private static final String BASEURL = "https://api.gettyimages.com/v3/search/";
    static Parcelable myParcel;
    public Controller(Context context){
        mContext = context;
    }

    private DataInterface mDataInterface;

    public void setDataInterfaceListener(DataInterface dataInterface) {
        mDataInterface = dataInterface;
    }

    public void start(){
        Gson gson  = new GsonBuilder().setLenient().create();

        Retrofit retfit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        final GettyImageAPI gettyImageAPI =  retfit.create(GettyImageAPI.class);


        Call<GettyImageModel> call = gettyImageAPI.loadImages("YOUR API KEY HERE");


        call.enqueue(new Callback<GettyImageModel>() {
            @Override
            public void onResponse(@NonNull Call<GettyImageModel> call, @NonNull Response<GettyImageModel> response) {

                gettyImageModel = response.body();
                assert gettyImageModel != null;
                Toast.makeText(mContext, gettyImageModel.getResultCount()+"",Toast.LENGTH_LONG).show();
                assert gettyImageModel != null;
                if(gettyImageModel.getResultCount()!=null) {
                    int count = gettyImageModel.getResultCount();
                    Log.i("Count", count + "");
                }
                mDataInterface.responseData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<GettyImageModel> call, @NonNull Throwable t) {
                    t.printStackTrace();
            }
        });
    }

   public interface DataInterface {

        void responseData(GettyImageModel gettyImageModel);
    }

}

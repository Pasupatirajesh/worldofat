package com.course.udacity.android.worldofat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class ActionModeImplementation implements ActionMode.Callback {

    private Context mContext;

    private dataCopiedListener mDataCopiedListener;

    public interface dataCopiedListener{

       String onDataCopied();

    }

    public void setDataCopiedListener(dataCopiedListener dataCopiedListener) {
        mDataCopiedListener = dataCopiedListener;
    }

    public ActionModeImplementation(Context context){
        this.mContext = context;

    }



    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) mContext;
        MenuInflater inflater = appCompatActivity.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

       AppCompatActivity activity = (AppCompatActivity) mContext;
       Objects.requireNonNull(activity.getSupportActionBar()).hide();
       return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()){
            case R.id.cut_copy_menu:
                // Get the ClipboardManager reference
                ClipboardManager clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

                ClipData clipData = ClipData.newPlainText("jobName",mDataCopiedListener.onDataCopied());
                assert clipboardManager != null;
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mContext, "Copied to Clipboard", Toast.LENGTH_LONG).show();
                mode.finish();
                break;

            case R.id.share_to_keep:
                // Get the Google Keep App
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra("KeepIntent", mDataCopiedListener.onDataCopied());
                PackageManager pm = mContext.getPackageManager();
                List<ResolveInfo> resolveActivity = pm.queryIntentActivities(sendIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = resolveActivity.size() > 0;
                if(isIntentSafe){
                    mContext.startActivity(sendIntent);
                }
                mode.finish();
                return true;

            default:
                return false;
        }

        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
       AppCompatActivity activity = (AppCompatActivity) mContext;
        Objects.requireNonNull(activity.getSupportActionBar()).show();


    }
}

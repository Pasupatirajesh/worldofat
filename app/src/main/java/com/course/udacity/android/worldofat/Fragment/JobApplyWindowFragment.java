package com.course.udacity.android.worldofat.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static com.course.udacity.android.worldofat.App.getContext;

public class JobApplyWindowFragment extends DialogFragment {

    private FirebaseAuth mAuth;
    private int RC_SIGN_IN= 1;
    public OnApplyCompletedListener onApplyCompletedListener;
    private static final String TAG = JobApplyWindowFragment.class.getSimpleName();

    public JobApplyWindowFragment() {

    }

    public interface OnApplyCompletedListener{

        Activity onApplyCompleted();

    }

    public static JobApplyWindowFragment newInstance (String title){
        JobApplyWindowFragment frag = new JobApplyWindowFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_frag_apply_window, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder alrtdb = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        alrtdb.setView(inflater.inflate(R.layout.dialog_frag_apply_window, null));

        String title = getArguments().getString("title");
        alrtdb.setTitle(title);
        alrtdb.setMessage("Apply to this job by emailing your resume");

        onClick();




        return alrtdb.create();
    }

    public void onClick(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_sign_in_key))
                .requestEmail().build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);

        Intent signIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            dismiss();
            Intent emailIntent = new Intent(Intent.ACTION_VIEW);
            Uri dat = Uri.parse("mailto:?subject=" +"HI"  + "&body=" + "applying for job");
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setData(dat);
            Intent chooserIntent = Intent.createChooser(emailIntent, getResources().getText(R.string.send_to));
            startActivity(chooserIntent);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            e.printStackTrace();
            Log.w(TAG, "SignInResult:failed code= "+ e.getStatusCode());

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(onApplyCompletedListener.onApplyCompleted(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.w(TAG, "signInWithCredential:Success " + task.getResult().getUser().getDisplayName());

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }


    @Override
    public void onAttach(Context context) {
        onApplyCompletedListener = (OnApplyCompletedListener) getActivity();
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        onApplyCompletedListener = null;
        super.onDetach();
    }


}

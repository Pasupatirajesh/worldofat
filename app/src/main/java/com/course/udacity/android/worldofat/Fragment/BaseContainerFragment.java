package com.course.udacity.android.worldofat.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.course.udacity.android.worldofat.R;


public class BaseContainerFragment extends Fragment {


    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if(addToBackStack){
            ft.addToBackStack(null);
        }
        ft.replace(R.id.fragment_atu_certificate, fragment);
        ft.commit();
        getChildFragmentManager().executePendingTransactions();
    }

    public boolean popFragment(){
        boolean isPop = false;
        if(getChildFragmentManager().getBackStackEntryCount() >0){
            isPop = true;
            getChildFragmentManager().popBackStack();
        }

        return isPop;
    }


}

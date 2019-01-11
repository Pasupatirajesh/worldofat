package com.course.udacity.android.worldofat.Fragment;

import com.course.udacity.android.worldofat.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


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

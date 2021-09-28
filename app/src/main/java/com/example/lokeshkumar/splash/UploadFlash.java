package com.example.lokeshkumar.splash;

/**
 * Created by lokesh kumar on 10/3/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFlash extends Fragment {
    public static int st=2000;

    public UploadFlash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), UploadFragment.class);
                UploadFlash.this.startActivity(intent);
            }
        },st);
        return inflater.inflate(R.layout.fragment_flash, container, false);
    }


}


package com.example.lokeshkumar.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class MusicFragment extends Fragment {
    public static int st=2000;

    public MusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                MusicFragment.this.startActivity(intent);
            }
        },st);
        return inflater.inflate(R.layout.fragment_music, container, false);
    }



}

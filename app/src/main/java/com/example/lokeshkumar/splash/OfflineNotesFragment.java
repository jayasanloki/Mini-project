package com.example.lokeshkumar.splash;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineNotesFragment extends Fragment {


    public OfflineNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offline_notes, container, false);

        final Button sendFreeTextButton = (Button) view.findViewById(R.id.click);
        sendFreeTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "sendFreeTextButton clicked");
                Intent intent = new Intent(getActivity(), OfflineNotes.class);
                OfflineNotesFragment.this.startActivity(intent);
            }
        });
        return view;
    }

}

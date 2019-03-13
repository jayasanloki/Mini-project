package com.example.lokeshkumar.splash;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.net.*;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.File;
import java.util.ArrayList;



public class DownloadActivity extends Activity implements View.OnClickListener{
    EditText act;
    TextView textView;
    File file;
    DatabaseReference database1;
    ListView listView;
    ArrayList<String> download_key= new ArrayList<>();
    ArrayList<String> download_value=new ArrayList<>();
    Button b1;
    String input;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        act = (EditText) findViewById(R.id.topic);

    input = act.getText().toString();
        b1=(Button)findViewById(R.id.downbut);
        b1.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.usersList1);
textView=(TextView)findViewById(R.id.textView3);

        //Create a new ArrayAdapter with your context and the simple layout for the dropdown menu provided by Android
        database1 =FirebaseDatabase.getInstance().getReference().child("download").child("sample");

        database1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    download_key.add(dsp.getKey());
                    download_value.add(dsp.getValue().toString());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void onClick(View v) {
        if (v.getId() == R.id.downbut) {
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(DownloadActivity.this,android.R.layout.simple_list_item_1,download_key);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    textView.setText(download_value.get(position).toString());
                    ClipboardManager cm = (ClipboardManager)DownloadActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(textView.getText());
                    Toast.makeText(DownloadActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            });
            //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Userlist1));
            Toast.makeText(this, "size"+download_value.size(), Toast.LENGTH_SHORT).show();
        }
    }
}

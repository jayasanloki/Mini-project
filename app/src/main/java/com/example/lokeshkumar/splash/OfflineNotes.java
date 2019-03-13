package com.example.lokeshkumar.splash;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class OfflineNotes extends Activity {
    EditText inputText;
    TextView response;
    Button saveButton,readButton;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
    Date now = new Date();
    private String filename = "pronotes_"+ formatter.format(now) +".txt";

    private String filepath = "Your offline notes/";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_notes);

        inputText = (EditText) findViewById(R.id.myInputText);
        response = (TextView) findViewById(R.id.response);


        saveButton = (Button) findViewById(R.id.saveExternalStorage);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
                        saveButton.setEnabled(false);
                    }
                    else {
                        myExternalFile = new File(getExternalFilesDir(filepath), filename);
                        FileOutputStream fos = new FileOutputStream(myExternalFile);
                        fos.write(inputText.getText().toString().getBytes());

                        fos.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                inputText.setText("");
                response.setText("File saved to Internal Storage/Android/data/com.example.lokeshkumar.splash/files/Your offline notes/...");
            }

        });

        readButton = (Button) findViewById(R.id.getExternalStorage);
        readButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        myData = myData + strLine+"\n";
                    }
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                inputText.setText(myData);
                response.setText("File data retrieved from Internal Storage...");
            }
        });




    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
public void fun6(View view)
{
    inputText = (EditText) findViewById(R.id.myInputText);
    inputText.setText("");
}

}
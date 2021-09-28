package com.example.lokeshkumar.splash;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class UploadFragment extends AppCompatActivity implements View.OnClickListener {


ImageButton uploadPDF,uploadImage,uploadWord,uploadVideo,uploadAudio,uploadText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);
        uploadPDF=(ImageButton)findViewById(R.id.pdf);
        uploadImage=(ImageButton)findViewById(R.id.gal);
        uploadText=(ImageButton)findViewById(R.id.textbutt);
        uploadWord=(ImageButton)findViewById(R.id.word);
        uploadVideo=(ImageButton)findViewById(R.id.video);
        uploadAudio=(ImageButton)findViewById(R.id.audio);
        uploadPDF.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
        uploadText.setOnClickListener(this);
                uploadWord.setOnClickListener(this);
        uploadVideo.setOnClickListener(this);
                uploadAudio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==uploadVideo) {

            startActivity(new Intent(this, UploadVideo.class));
        }
        else if(view==uploadWord)
        {

                startActivity(new Intent(this, UploadWord.class));

        }
        else if(view==uploadImage)
        {

            startActivity(new Intent(this, UploadImage.class));

        }
        else if(view==uploadPDF)
        {

            startActivity(new Intent(this, UploadPdf.class));

        }
        else if(view==uploadText)
        {

            startActivity(new Intent(this, UploadTextDoc.class));

        }
        else if(view==uploadAudio)
        {

            startActivity(new Intent(this, UploadAudio.class));

        }
    }
    }







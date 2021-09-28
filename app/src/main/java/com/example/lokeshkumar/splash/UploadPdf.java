package com.example.lokeshkumar.splash;

/**
 * Created by lokesh kumar on 10/3/2017.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class UploadPdf extends AppCompatActivity implements View.OnClickListener {
    final static int PICK_PDF_CODE = 2342;
    private Uri filePath;
    EditText editTextFilename;
    ProgressBar progressBar;
    StorageReference mStorageReference;
    Button buttonUploadpdf,buttonChoosePdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_pdf);
        buttonUploadpdf = (Button) findViewById(R.id.buttonUploadpdf);
        buttonChoosePdf=(Button) findViewById(R.id.buttonChoosepdf);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        editTextFilename = (EditText) findViewById(R.id.uppdf);

        buttonUploadpdf.setOnClickListener(this);
        buttonChoosePdf.setOnClickListener(this);
    }
    private void getPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), PICK_PDF_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if (data.getData() != null) {
                String home = Environment.getExternalStorageDirectory().toString();
                File file = new File(home);
                if(file.exists())
                {
                    file.getAbsolutePath();
                }
                else
                {
                    Toast.makeText(this,"no dir extists",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void uploadPDFFile() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            StorageReference riversRef = mStorageReference.child(editTextFilename.getText().toString());
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        else {
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonChoosePdf) {
            getPDF();
        }
        //if the clicked button is upload
        else if (view == buttonUploadpdf) {
            if(editTextFilename.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(), "Enter the file name ", Toast.LENGTH_LONG).show();
            }
            else {
                uploadPDFFile();
            }

        }
    }
}
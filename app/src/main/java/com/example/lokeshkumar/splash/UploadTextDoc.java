package com.example.lokeshkumar.splash;

/**
 * Created by lokesh kumar on 10/3/2017.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class UploadTextDoc extends AppCompatActivity implements View.OnClickListener {
    final static int PICK_PDF_CODE = 2342;
    short code, codeword;
    char c;
    String s;
    FileInputStream fis;
    InputStreamReader rdr;
    FileOutputStream fos;
    ObjectOutputStream fout;
    private Uri filePath;
    private Uri filePath1;
    EditText editTextFilename;
    public HashMap compdic, decompdic;
    String fileName,filepathh;
    short lastcode = 0, dlastcode = 0;
    TextView response;
    StorageReference mStorageReference;
    Button buttonUploadpdf,buttonChoosePdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_text_doc);
        buttonUploadpdf = (Button) findViewById(R.id.buttonUploaddoc);
        buttonChoosePdf=(Button) findViewById(R.id.buttonChoosedoc);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        editTextFilename = (EditText) findViewById(R.id.updoc);
response=(TextView)findViewById(R.id.textView2);
        buttonUploadpdf.setOnClickListener(this);
        buttonChoosePdf.setOnClickListener(this);
        compdic = new HashMap<String, Integer>();
        decompdic = new HashMap<Integer, String>();
        createDictionary();
    }
    public void createDictionary() {
        try {
            short code;
            char ch;
            FileInputStream fis = new FileInputStream(fileName);
            InputStreamReader rdr = new InputStreamReader(fis, "utf-8");
            while ((code = (short) rdr.read()) != -1) {
                ch = (char) code;

                if (!compdic.containsKey(ch)) {
                    compdic.put("" + ch, code);
                    decompdic.put(code, "" + ch);
                    if (code > lastcode) {
                        lastcode = code;
                        dlastcode = code;
                    }
                }
            }
            fis.close();
        } catch (Exception ex) {
            Logger.getLogger(LZW.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getPDF() {
        Intent intent = new Intent();
        intent.setType("text/plain");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Text Document File"), PICK_PDF_CODE);
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

                response.setText(filePath.toString()+"1");
            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
            fileName=filePath.toString();
        }
    }
    private void uploadPDFFile() {

        if (filePath != null) {
            try {



                fis = new FileInputStream(fileName);
                rdr = new InputStreamReader(fis, "utf-8");
                fos = new FileOutputStream(fileName+"1");
                fout = new ObjectOutputStream(fos);

                s = (char) rdr.read() + "";
                while ((code = (short) rdr.read()) != -1) {
                    c = (char) code;

                    if (!compdic.containsKey(s + c)) {
                        codeword = Short.parseShort(compdic.get(s).toString());

                        fout.writeShort(codeword);
                        compdic.put(s + c, ++lastcode);
                        s = "" + c;
                    } else {
                        s = s + c;
                    }
                }

                codeword = Short.parseShort(compdic.get(s).toString());
                fout.writeShort(codeword);
                fout.writeShort(00);
                fout.close();
                fis.close();



            } catch (Exception ex) {
                Logger.getLogger(UploadTextDoc.class.getName()).log(Level.SEVERE, null, ex);
            }
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

filePath1=Uri.parse(fileName);
            StorageReference riversRef = mStorageReference.child(editTextFilename.getText().toString());
            riversRef.putFile(filePath1)
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
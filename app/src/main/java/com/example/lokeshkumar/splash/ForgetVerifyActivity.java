package com.example.lokeshkumar.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lokesh kumar on 9/3/2017.
 */

public class ForgetVerifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_verify_activity);
    }

    public void fun3(View view) {
        String bt;
        bt = ((Button) view).getText().toString();
        if (bt.equals("CHANGE")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

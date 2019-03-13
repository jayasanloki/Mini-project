package com.example.lokeshkumar.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lokesh kumar on 9/3/2017.
 */

public class ForgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);
    }

    public void fun2(View view) {
        String bt;
        bt = ((Button) view).getText().toString();
        if (bt.equals("SUBMIT")) {
            Intent intent = new Intent(this, ForgetVerifyActivity.class);
            startActivity(intent);
        }
    }
}


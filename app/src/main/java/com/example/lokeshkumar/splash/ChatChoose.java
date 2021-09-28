package com.example.lokeshkumar.splash;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lokesh kumar on 10/23/2017.
 */

public class ChatChoose extends AppCompatActivity implements View.OnClickListener {

Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_choose);
    b1=(Button)findViewById(R.id.pc);
        b2=(Button)findViewById(R.id.gc);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.pc)
        {
            Intent intent=new Intent(this,ChatLogin.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.gc)
        {
            Intent intent=new Intent(this,PublicChat.class);
            startActivity(intent);
        }
    }
}

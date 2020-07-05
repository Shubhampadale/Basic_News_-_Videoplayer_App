package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        sp=getSharedPreferences("APP_NAME",MODE_PRIVATE);
        editor=sp.edit();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.contains("my_email")&& sp.contains("my_pass")){
                    Intent i=new Intent(splashscreen.this,homeactivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(splashscreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },2500);
    }
}

package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homeactivity extends AppCompatActivity {
    Button button_news,button_videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

        button_news=findViewById(R.id.btn_news);
        button_videos=findViewById(R.id.btn_youtube);

        button_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(homeactivity.this,listnews_activity.class);
                startActivity(i);

            }
        });

        button_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(homeactivity.this,video_input.class);
                startActivity(i);
            }
        });
    }
}

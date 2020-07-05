package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class videoview3 extends YouTubeBaseActivity {

    private static final String TAG= "videoview1";

    YouTubePlayerView mYouTubePlayerView;
    TextView textView_publish,textView_description,textView_data,textView_data2;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview3);

        Log.d(TAG,"onCreate : Starting.");
        mYouTubePlayerView=findViewById(R.id.youtubeplay);

        textView_publish=findViewById(R.id.txt_publish);
        textView_data=findViewById(R.id.title_data);
        textView_description=findViewById(R.id.txt_description);
        textView_data2=findViewById(R.id.txt_data);

        String url="https://www.googleapis.com/youtube/v3/videos?id=HK6B2da3DPA&key=AIzaSyDM7CwAcEtwea4SSLvwu-OiMU3QFR66aZ8&part=snippet,contentDetails,statistics,status";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    JSONObject emp=jsonArray.getJSONObject(0);

                    JSONObject emp2=emp.getJSONObject("snippet");

                    String publish=emp2.getString("publishedAt");
                    textView_publish.setText(publish);

                    String title= emp2.getString("title");
                    textView_data.setText(title);

                    String description = emp2.getString("description");
                    textView_data2.setText(description);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG,"onClick :Done  Initialising .");


                youTubePlayer.loadVideo("HK6B2da3DPA");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG,"onClick : Failed to Initialising .");
            }
        };

        mYouTubePlayerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick : Initialising youtube player.");
                mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);
            }
        });

    }
}

package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detail_news_4 extends AppCompatActivity {
    TextView textView,textView1,textView2;
    ArrayList<String> titles;
    ImageView imageView;
    //String url="https://ichef.bbci.co.uk/news/1024/branded_news/119A1/production/_110579027_gettyimages-495745734.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        textView=findViewById(R.id.txt_detail);
        textView1=findViewById(R.id.txt_detail_1);
        textView2=findViewById(R.id.txt_detail_2);
        imageView=findViewById(R.id.news_img);

        String URL="https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=601458958be649e4bc166b1abc4423d9";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("articles");
                    JSONObject emp=jsonArray.getJSONObject(4);

                    String url=emp.getString("urlToImage");
                    loadImageFromUrl(url);

                    String description=emp.getString("description");
                    textView.setText(description);

                    String content=emp.getString("content");
                    if(content!="null"){
                        textView1.setText(content);
                    }

                    String publish=emp.getString("publishedAt");
                    textView2.setText(publish);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(imageView, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}


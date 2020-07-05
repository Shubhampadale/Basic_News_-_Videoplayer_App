package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listnews_activity extends AppCompatActivity {
    ListView listView_news;
    ArrayList<String> titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listnews_activity);
        listView_news=findViewById(R.id.list_news);

        titles=new ArrayList<>();

        String URL="https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=601458958be649e4bc166b1abc4423d9";
        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json=new JSONObject(response);
                    JSONArray articles=json.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++){
                        String s1=json.getJSONArray("articles").getJSONObject(i).getString("title");
                        titles.add(s1);
                    }
                    final ArrayAdapter ad=new ArrayAdapter<String>(listnews_activity.this,android.R.layout.simple_list_item_1,titles);
                    listView_news.setAdapter(ad);
                   /* listView_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String value= String.valueOf(ad.getItem(position));
                            if(titles.equals(value)){
                                Intent i=new Intent(listnews_activity.this,detail_news.class);
                                startActivity(i);
                            }

                        }
                    });*/
                   listView_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           if(position==0){
                               Intent i=new Intent(listnews_activity.this,detail_news.class);
                               startActivity(i);
                           }
                           if(position==1){
                               Intent i=new Intent(listnews_activity.this,detail_news_1.class);
                               startActivity(i);
                           }
                           if(position==2){
                               Intent i=new Intent(listnews_activity.this,detail_news_2.class);
                               startActivity(i);
                           }
                           if(position==3){
                               Intent i=new Intent(listnews_activity.this,detail_news_3.class);
                               startActivity(i);
                           }
                           if(position==4){
                               Intent i=new Intent(listnews_activity.this,detail_news_4.class);
                               startActivity(i);
                           }
                           if(position==5){
                               Intent i=new Intent(listnews_activity.this,detail_news_5.class);
                               startActivity(i);
                           }
                           if(position==6){
                               Intent i=new Intent(listnews_activity.this,detail_news_6.class);
                               startActivity(i);
                           }
                           if(position==7){
                               Intent i=new Intent(listnews_activity.this,detail_news_7.class);
                               startActivity(i);
                           }
                           if(position==8){
                               Intent i=new Intent(listnews_activity.this,detail_news_8.class);
                               startActivity(i);
                           }
                           if(position==9){
                               Intent i=new Intent(listnews_activity.this,detail_news_9.class);
                               startActivity(i);
                           }
                       }
                   });



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




    }
}

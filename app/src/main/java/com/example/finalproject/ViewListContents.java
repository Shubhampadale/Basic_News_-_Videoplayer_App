package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewListContents extends Activity {

    DatabaseHelper myDB;
    TextView textView_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents_layout);

        textView_list=findViewById(R.id.txt_list);
        final ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent i=new Intent(ViewListContents.this,videoview.class);
                    startActivity(i);
                }

                if(position==1){
                    Intent i=new Intent(ViewListContents.this,videoview1.class);
                    startActivity(i);
                }

                if(position==2){
                    Intent i=new Intent(ViewListContents.this,videoview2.class);
                    startActivity(i);
                }

                if(position==3){
                    Intent i=new Intent(ViewListContents.this,videoview3.class);
                    startActivity(i);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                theList.remove(position);
                listView.deferNotifyDataSetChanged();
                Toast.makeText(ViewListContents.this, "Item deleted", Toast.LENGTH_SHORT).show();
                return  true;
            }
        });




    }
}


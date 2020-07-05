package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText_email,editText_pass;
    Button button_login,button_register;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_email=findViewById(R.id.edt_email);
        editText_pass=findViewById(R.id.edt_pass);
        button_login=findViewById(R.id.btn_login);
        button_register=findViewById(R.id.btn_register);

        sp=getSharedPreferences("APP_NAME",MODE_PRIVATE);
        editor=sp.edit();




        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText_email.getText().toString().trim();
                String pass=editText_pass.getText().toString().trim();

                if (email.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill the Empty Fields", Toast.LENGTH_SHORT).show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editText_email.setError("Invalid Email ID");

                }else if (pass.length()<6){
                    editText_pass.setError("Invalid Password");
                }else{
                    editor.putString("my_email",email);
                    editor.putString("my_pass",pass);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(MainActivity.this,homeactivity.class);
                    startActivity(i);
                    finish();
                    clear();
                }

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,register_activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void clear() {
        editText_email.setText("");
        editText_pass.setText("");
    }
}

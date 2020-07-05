package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register_activity extends AppCompatActivity {
    EditText editText_name,editText_email,editText_pass,editText_phone,editText_city,editText_cpass;
    Button button_signup;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        editText_name=findViewById(R.id.edt_name);
        editText_email=findViewById(R.id.edt_email);
        editText_pass=findViewById(R.id.edt_pass);
        editText_phone=findViewById(R.id.edt_phone);
        editText_city=findViewById(R.id.edt_city);
        editText_cpass=findViewById(R.id.edt_cpass);
        button_signup=findViewById(R.id.btn_signup);

        sp=getSharedPreferences("APP_NAME",MODE_PRIVATE);
        editor=sp.edit();

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editText_name.getText().toString().trim();
                String email=editText_email.getText().toString().trim();
                String pass=editText_pass.getText().toString().trim();
                String cpass=editText_cpass.getText().toString().trim();
                String phone=editText_phone.getText().toString().trim();
                String city=editText_city.getText().toString().trim();

                if (name.equals("")|| email.equals("")|| pass.equals("")|| phone.equals("")|| city.equals("")){
                    Toast.makeText(register_activity.this, "Please Fill Empty Fields", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editText_email.setError("Invalid Email Id");
                }else if (pass.length()<6){
                    editText_pass.setError("Invalid Password");
                }else if (!cpass.equals(pass)){
                    editText_cpass.setError("Invalid Password");
                }
                else if ( phone.length()>10){
                    editText_phone.setError("Invalid Number");
                } else{
                    editor.putString("my_email1",email);
                    editor.putString("my_pass1",pass);
                    editor.commit();
                    Toast.makeText(register_activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(register_activity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    clear();
                }
            }
        });
    }

    private void clear() {
        editText_name.setText("");
        editText_email.setText("");
        editText_pass.setText("");
        editText_cpass.setText("");
        editText_phone.setText("");
        editText_city.setText("");
    }
}

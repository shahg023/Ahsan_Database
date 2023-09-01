package com.example.databaselinkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DB_Helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.usernamelogin);
        EditText password = findViewById(R.id.passwordlogin);
        Button login = findViewById(R.id.loginbtnuser);
        DB =new DB_Helper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

               if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                   Toast.makeText(login.this, "All Fields Required", Toast.LENGTH_SHORT).show();
               }
               else {
                   Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                   if (checkuserpass) {
                       Toast.makeText(login.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(getApplicationContext(), home.class);
                       startActivity(intent);
                   }
                   else {
                       Toast.makeText( login.this, "Login Failed" ,Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
    }
}
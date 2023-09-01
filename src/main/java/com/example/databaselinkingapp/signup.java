package com.example.databaselinkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    DB_Helper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText username = findViewById(R.id.usernamesign);
        EditText password = findViewById(R.id.passwordsign);
        EditText cpassword = findViewById(R.id.cpasswordsign);
        Button signup = findViewById(R.id.signbtnuser);
        DB = new DB_Helper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String cPass = cpassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(cPass)) {
                    Toast.makeText(signup.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(cPass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertdata(user, pass);
                            if (insert == true) {
                                Toast.makeText(signup.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signup.this, "Try Again", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(signup.this, "Username Exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(signup.this, "Password Not Match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
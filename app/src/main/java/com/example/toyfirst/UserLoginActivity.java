package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView registerHere;
    AppCompatButton login;
    loginDBHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = findViewById(R.id.login_username_txt);
        password = findViewById(R.id.login_password_txt);

        login = findViewById(R.id.login_login_btn);
        registerHere = findViewById(R.id.register_here);

        loginHelper = new loginDBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(UserLoginActivity.this,"All Fields Required", Toast.LENGTH_SHORT).show();

                }else {
                    Boolean check = loginHelper.checkUsernamePassword(user,pass);

                    if (check == true){
                        Toast.makeText(UserLoginActivity.this,"Login Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(UserLoginActivity.this,"Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
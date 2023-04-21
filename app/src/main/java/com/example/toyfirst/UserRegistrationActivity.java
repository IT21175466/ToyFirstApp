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

public class UserRegistrationActivity extends AppCompatActivity {

    EditText username, email, password;
    TextView loginHere;
    AppCompatButton register;
    loginDBHelper loginHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        username = findViewById(R.id.username_txt);
        email = findViewById(R.id.email_txt);
        password = findViewById(R.id.password_txt);

        register = findViewById(R.id.register_btn);
        loginHere = findViewById(R.id.login_here);

        loginHelper = new loginDBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String e_mail = email.getText().toString();
                String pass_word = password.getText().toString();

                if (TextUtils.isEmpty(user_name) || TextUtils.isEmpty(pass_word) || TextUtils.isEmpty(e_mail)){
                    Toast.makeText(UserRegistrationActivity.this,"All Fields Required", Toast.LENGTH_SHORT).show();

                }else {
                    Boolean insert = loginHelper.insertUsers(user_name,e_mail,pass_word);

                    if (insert == true){
                        Toast.makeText(UserRegistrationActivity.this,"Registration Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),UserLoginActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(UserRegistrationActivity.this,"Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
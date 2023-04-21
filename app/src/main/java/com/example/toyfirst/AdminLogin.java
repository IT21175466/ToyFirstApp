package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText username, password;
    AppCompatButton adminLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.admin_login_username_txt);
        password = findViewById(R.id.admin_login_password_txt);

        adminLogin = findViewById(R.id.admin_login_btn);

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a_username = username.getText().toString();
                String a_password = password.getText().toString();

                if (TextUtils.isEmpty(a_username) || TextUtils.isEmpty(a_password)){
                    Toast.makeText(AdminLogin.this,"All Fields Required", Toast.LENGTH_SHORT).show();

                }else {

                    String admin_username = "Admin";
                    String admin_Pass = "Admin123";

                    if (admin_username != a_username || admin_Pass != a_password){
                        Toast.makeText(AdminLogin.this,"Login Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AdminPanel.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
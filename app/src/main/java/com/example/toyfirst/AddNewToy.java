package com.example.toyfirst;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddNewToy extends AppCompatActivity {

    private EditText name;
    private EditText price;
    private EditText quantity;
    private EditText category;

    private AppCompatButton addToy;

    private DBHelper dbHelper;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_toy);

        name = findViewById(R.id.toy_name_txt);
        price = findViewById(R.id.toy_price_txt);
        quantity = findViewById(R.id.toy_quantity_txt);
        addToy = findViewById(R.id.add_toy_btn);
        category = findViewById(R.id.toy_category_txt);

        context = this;

        dbHelper = new DBHelper(context);

        addToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u_name = name.getText().toString();
                String u_price = price.getText().toString();
                String u_quantity = quantity.getText().toString();
                String u_category = category.getText().toString();

                Toy toy = new Toy(u_name,u_price,u_quantity, u_category);
                dbHelper.addToy(toy);

                startActivity(new Intent(AddNewToy.this,AdminPanel.class));
            }
        });
    }
}
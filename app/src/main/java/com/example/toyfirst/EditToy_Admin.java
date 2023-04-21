package com.example.toyfirst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class EditToy_Admin extends AppCompatActivity {

    private EditText update_toy_name;
    private EditText update_toy_price;
    private EditText update_toy_quantity;
    private EditText update_toy_category;

    private AppCompatButton update_toy;

    private Context context;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_toy_admin);

        context = this;
        dbHelper = new DBHelper(context);

        update_toy_name = findViewById(R.id.update_toy_name_txt);
        update_toy_price = findViewById(R.id.update_toy_price_txt);
        update_toy_quantity = findViewById(R.id.update_toy_quantity_txt);
        update_toy_category = findViewById(R.id.update_toy_category_txt);

        update_toy = findViewById(R.id.update_toy_btn);

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);
        Toy toy = dbHelper.getSingleToy(Integer.parseInt(id));

        update_toy_name.setText(toy.getTitle());
        update_toy_price.setText(toy.getPrice());
        update_toy_quantity.setText(toy.getQuantity());
        update_toy_category.setText(toy.getCategory());

        update_toy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = update_toy_name.getText().toString();
                String price = update_toy_price.getText().toString();
                String quantity = update_toy_quantity.getText().toString();
                String category = update_toy_category.getText().toString();

                Toy toy = new Toy(Integer.parseInt(id), name, price, quantity, category);
                int status = dbHelper.updateToy(toy);
                System.out.println(status);
                startActivity(new Intent(context,AdminPanel.class));
                finish();
            }
        });
    }

}
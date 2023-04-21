package com.example.toyfirst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    private ListView listView, listView1;
    private DBHelper dbHelper;
    private Cart_DB_Helper cart_db_helper;
    private List<Toy> toys;
    private List<CategoryHandler> ca_handler;
    Context context;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imageView = findViewById(R.id.goto_cart);

        context = this;

        dbHelper = new DBHelper(context);
        cart_db_helper = new Cart_DB_Helper(context);
        listView = findViewById(R.id.userToyList);
        listView1 = findViewById(R.id.categoryList);
        toys = new ArrayList<>();
        ca_handler = new ArrayList<>();

        toys = dbHelper.getAllToys();
        ToyAdapter adapter = new ToyAdapter(context, R.layout.singleitem,toys);
        listView.setAdapter(adapter);

        ca_handler = dbHelper.getAllCategories();
        CategoryAdapter categoryAdapter = new CategoryAdapter(context, R.layout.single_category,ca_handler);
        listView1.setAdapter(categoryAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toy toy = toys.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(toy.getTitle());
                builder.setMessage("Price - "+toy.getPrice() + "\nQuantity - "+toy.getQuantity());

                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(UserProfile.this,UserProfile.class));
                        finish();
                    }
                });

                builder.setNegativeButton("View Cart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(UserProfile.this,ShoppingCart.class));
                    }
                });

                builder.setNeutralButton("Add to Cart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //get toy details
                        String cname = String.valueOf(toy.getTitle());
                        String price = String.valueOf(toy.getPrice());

                        Shopping_Cart_Model shopping_cart_model = new Shopping_Cart_Model(cname,price);
                        cart_db_helper.addToCART(shopping_cart_model);

                        Toast.makeText(UserProfile.this,"Successfully added to the cart",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this,ShoppingCart.class));
            }
        });
    }
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(UserProfile.this,MainActivity.class));
        finish();
    }
}
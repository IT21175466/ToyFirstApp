package com.example.toyfirst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdminPanel extends AppCompatActivity {

    private AppCompatButton add;
    private ImageView order_btn;
    private ListView listView;
    private DBHelper dbHelper;
    private List<Toy> toys;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        context = this;

        dbHelper = new DBHelper(context);
        add = findViewById(R.id.add_new_toy);
        listView = findViewById(R.id.toyList);
        toys = new ArrayList<>();
        order_btn = findViewById(R.id.admin_order_btn);

        toys = dbHelper.getAllToys();

        ToyAdapter adapter = new ToyAdapter(context, R.layout.singleitem,toys);

        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,AddNewToy.class));
                finish();
            }
        });

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
                        startActivity(new Intent(AdminPanel.this,AdminPanel.class));
                        finish();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHelper.deleteToy(toy.getId());
                        startActivity(new Intent(AdminPanel.this,AdminPanel.class));
                        finish();
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, EditToy_Admin.class);
                        intent.putExtra("id", String.valueOf(toy.getId()));
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminPanel.this,AdminOrder.class));
            }
        });

    }
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(AdminPanel.this,MainActivity.class));
        finish();
    }
}
package com.example.toyfirst;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdminOrder extends AppCompatActivity {

    private ListView listView;
    Context context;
    private List<Shopping_Cart_Model> cartModelList;
    Cart_DB_Helper cart_db_helper;
    TextView bill_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);

        context = this;

        listView = findViewById(R.id.cartList);
        cart_db_helper = new Cart_DB_Helper(context);
        cartModelList = new ArrayList<>();
        bill_amount = findViewById(R.id.bill_amount_txt);

        cartModelList = cart_db_helper.getAllCartItems();

        Shopping_Cart_Model shopping_cart_model = new Shopping_Cart_Model();

        Cart_Adapter cart_adapter = new Cart_Adapter(context,R.layout.single_cart_item, cartModelList);

        listView.setAdapter(cart_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Shopping_Cart_Model shoppingCartModel = cartModelList.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(shoppingCartModel.getCart_title());
                builder.setMessage(shoppingCartModel.getCart_price());

                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(AdminOrder.this,AdminOrder.class));
                        finish();
                    }
                });

                builder.setNegativeButton("Accept Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cart_db_helper.deleteCartItem(shoppingCartModel.getCart_id());
                        startActivity(new Intent(AdminOrder.this,AdminPanel.class));
                        finish();
                    }
                });

                builder.show();

            }
        });
    }
}
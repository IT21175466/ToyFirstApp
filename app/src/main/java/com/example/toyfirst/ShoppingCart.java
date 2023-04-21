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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends AppCompatActivity {

    private ListView listView;
    private Cart_DB_Helper cart_db_helper;
    Context context;
    private List<Shopping_Cart_Model> cartModelList;
    TextView bill_amount;
    AppCompatButton calcbill;
    AppCompatButton placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        context = this;

        listView = findViewById(R.id.cartList);
        cart_db_helper = new Cart_DB_Helper(context);
        cartModelList = new ArrayList<>();
        bill_amount = findViewById(R.id.bill_amount_txt);
        calcbill = findViewById(R.id.calc_bill_btn);
        placeOrder = findViewById(R.id.place_order_btn);

        cartModelList = cart_db_helper.getAllCartItems();

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
                        startActivity(new Intent(ShoppingCart.this,ShoppingCart.class));
                        finish();
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cart_db_helper.deleteCartItem(shoppingCartModel.getCart_id());
                        startActivity(new Intent(ShoppingCart.this,ShoppingCart.class));
                        finish();
                    }
                });

                builder.show();

            }
        });

        calcbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bill_amount.setText(String.format(cart_db_helper.getPrice()));
                /*String price = String.valueOf(cart_db_helper.getPrice());
                System.out.println(price);*/
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShoppingCart.this,"Order Placed Successfully!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(ShoppingCart.this, UserProfile.class);
                startActivity(i);
            }
        });
    }
}
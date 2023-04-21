package com.example.toyfirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Cart_Adapter extends ArrayAdapter<Shopping_Cart_Model> {

    private Context context;
    private int resources;
    List<Shopping_Cart_Model> shopping_cart_models;

    Cart_Adapter(Context context, int resource, List<Shopping_Cart_Model> cart_model){
        super(context, resource, cart_model);
        this.context = context;
        this.resources = resource;
        this.shopping_cart_models = cart_model;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resources,parent,false);

        TextView cart_name = row.findViewById(R.id.cart_title_txt);
        TextView cart_price = row.findViewById(R.id.cart_price_txt);

        Shopping_Cart_Model shopping_cart_model = shopping_cart_models.get(position);

        cart_name.setText(shopping_cart_model.getCart_title());
        cart_price.setText(shopping_cart_model.getCart_price());

        return row;
    }
}

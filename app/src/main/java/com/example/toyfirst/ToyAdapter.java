package com.example.toyfirst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ToyAdapter extends ArrayAdapter<Toy> {

    private Context context;
    private int resources;
    List<Toy> toys;

    ToyAdapter(Context context, int resource, List<Toy> toys){
        super(context, resource, toys);
        this.context = context;
        this.resources = resource;
        this.toys = toys;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resources,parent,false);

        TextView name = row.findViewById(R.id.title_txt);
        TextView price = row.findViewById(R.id.price_txt);
        TextView quantity = row.findViewById(R.id.quantity_txt);
        TextView category = row.findViewById(R.id.category_txt);

        Toy toy = toys.get(position);

        name.setText(toy.getTitle());
        price.setText(toy.getPrice());
        quantity.setText(toy.getQuantity());
        category.setText(toy.getCategory());

        return row;
    }
}

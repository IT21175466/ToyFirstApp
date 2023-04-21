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

public class CategoryAdapter extends ArrayAdapter<CategoryHandler> {

    private Context context;
    private int resources;
    List<CategoryHandler> categoryHandlers;

    public CategoryAdapter(Context context, int resource, List<CategoryHandler> categoryHandlers) {
        super(context, resource, categoryHandlers);
        this.context = context;
        this.resources = resource;
        this.categoryHandlers = categoryHandlers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resources,parent,false);

        TextView categories = row.findViewById(R.id.category_txt_single);

        CategoryHandler categoryHandler = categoryHandlers.get(position);
        categories.setText(categoryHandler.getCategoryName());

        return row;
    }

}

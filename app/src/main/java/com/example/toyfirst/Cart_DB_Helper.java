package com.example.toyfirst;

import static android.os.Build.ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Cart_DB_Helper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String CART_DB_NAME = "cartdb";

    private static final String CART_TABLE_NAME = "cart";

    //COLUMNS
    private static final String CART_ID = "cart_id";
    private static final String CART_TITLE = "cart_title";
    private static final String CART_PRICE = "cart_price";

    public Cart_DB_Helper(@Nullable Context context) {
        super(context, CART_DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CART_TABLE_CREATE_QUERY = "CREATE TABLE " + CART_TABLE_NAME+ " " +
                "("
                +CART_ID+" INTEGER PRIMARY KEY " + "AUTOINCREMENT,"
                +CART_TITLE+ " TEXT,"
                +CART_PRICE+" TEXT" +
                ");";

        sqLiteDatabase.execSQL(CART_TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String CART_DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + CART_TABLE_NAME;
        sqLiteDatabase.execSQL(CART_DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);

    }

    public void addToCART(Shopping_Cart_Model shopping_cart_model){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues2 = new ContentValues();

        //structure
        contentValues2.put(CART_TITLE, shopping_cart_model.getCart_title());
        contentValues2.put(CART_PRICE, shopping_cart_model.getCart_price());

        //Add to db
        sqLiteDatabase.insert(CART_TABLE_NAME,null, contentValues2);
        sqLiteDatabase.close();
    }

    public List<Shopping_Cart_Model> getAllCartItems() {
        List<Shopping_Cart_Model> scm = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + CART_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {

            do {
                Shopping_Cart_Model shopping_cart_model = new Shopping_Cart_Model();

                shopping_cart_model.setCart_id(cursor.getInt(0));
                shopping_cart_model.setCart_title(cursor.getString(1));
                shopping_cart_model.setCart_price(cursor.getString(2));

                scm.add(shopping_cart_model);

            } while (cursor.moveToNext());

        }
        return scm;

    }

    //Delete
    public void deleteCartItem(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(CART_TABLE_NAME, CART_ID +" =?",new String[]{String.valueOf(id)});
        db.close();
    }

    //Select prices
    public String getPrice(){
        SQLiteDatabase db = getReadableDatabase();
        String price;
        String query = "SELECT SUM(cart_price) FROM " + CART_TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){

            price = String.valueOf(cursor.getInt(0));

        }else {
            price = "0";

        }
        cursor.close();
        db.close();

        return price;
    }
}

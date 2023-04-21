package com.example.toyfirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "toyfirst";
    private static final String TABLE_NAME = "toys";

    //Columns
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";
    private static final String CATEGORY = "category";

    //CART
    private static final String CART_TABLE_NAME = "cart";

    //COLUMNS
    private static final String CART_ID = "cart_id";
    private static final String CART_TITLE = "cart_title";
    private static final String CART_PRICE = "cart_price";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME+ " " +
                "("
                +ID+" INTEGER PRIMARY KEY " + "AUTOINCREMENT,"
                +TITLE+ " TEXT,"
                +PRICE+" TEXT,"
                +QUANTITY+" TEXT,"
                +CATEGORY+" TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        onCreate(sqLiteDatabase);
    }

    //Add item to db
    public void addToy(Toy toy){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //structure
        contentValues.put(TITLE, toy.getTitle());
        contentValues.put(PRICE, toy.getPrice());
        contentValues.put(QUANTITY, toy.getQuantity());
        contentValues.put(CATEGORY, toy.getCategory());

        //Add to db
        sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        sqLiteDatabase.close();
    }

    //Get all items
    public List<Toy> getAllToys(){
        List<Toy> toys = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){

            do {
                Toy toy = new Toy();

                toy.setId(cursor.getInt(0));
                toy.setTitle(cursor.getString(1));
                toy.setPrice(cursor.getString(2));
                toy.setQuantity(cursor.getString(3));
                toy.setCategory(cursor.getString(4));

                toys.add(toy);

            }while (cursor.moveToNext());
        }

        return toys;
    }

    //Delete
    public void deleteToy(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID +" =?",new String[]{String.valueOf(id)});
    }

    //select single toy
    public Toy getSingleToy(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{ID, TITLE, PRICE, QUANTITY, CATEGORY},
                ID + "= ?", new String[]{String.valueOf(id)}
        ,null,null,null);

        Toy toy;
        if (cursor != null){
            cursor.moveToFirst();
            toy = new Toy(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            return toy;
        }
        return null;
    }

    //Update
    public int updateToy(Toy toy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, toy.getTitle());
        contentValues.put(PRICE, toy.getPrice());
        contentValues.put(QUANTITY, toy.getQuantity());
        contentValues.put(CATEGORY, toy.getCategory());

        int status = db.update(TABLE_NAME, contentValues, ID + " =?",
                new String[]{String.valueOf(toy.getId())});

        db.close();
        return status;
    }

    //Get categories
    public List<CategoryHandler> getAllCategories(){
        List<CategoryHandler> categoryLIST = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){

            do {
                CategoryHandler categoryHandler = new CategoryHandler();

                categoryHandler.setCategoryName(cursor.getString(4));

                categoryLIST.add(categoryHandler);

            }while (cursor.moveToNext());
        }

        return categoryLIST;

    }

}

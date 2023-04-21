package com.example.toyfirst;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class loginDBHelper extends SQLiteOpenHelper {

    Context context;

    private static final int VERSION = 1;
    private static final String REG_DB_NAME = "login";
    private static final String REG_TABLE_NAME = "users";

    //Columns
    private static final String REG_ID = "id";
    private static final String REG_USERNAME = "username";
    private static final String REG_EMAIL = "email";
    private static final String REG_PASSWORD = "password";

    public loginDBHelper(@Nullable Context context) {
        super(context, REG_DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE " + REG_TABLE_NAME+ " " +
                "("
                +REG_ID+" INTEGER PRIMARY KEY " + "AUTOINCREMENT,"
                +REG_USERNAME+ " TEXT,"
                +REG_EMAIL+" TEXT,"
                +REG_PASSWORD+" TEXT" +
                ");";

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //Add Users
    public boolean insertUsers(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(REG_USERNAME, username);
        contentValues.put(REG_EMAIL, email);
        contentValues.put(REG_PASSWORD, password);

        long result = db.insert(REG_TABLE_NAME, null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }

    }
}

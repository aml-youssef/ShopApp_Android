package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String key_rowid = "product_id";
    public static final String key_name = "product_name";
    public static final String key_description = "product_disc";
    public static final String key_photo = "product_photo";
    public static final String key_price = "product_price";
    public static final String key_amount = "product_amount";
    public static final String key_seller = "seller_id";
    private final String DB_Table = "Products_Table";
    public DBHelper(Context context) {
        super(context, "ProductDB", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = "CREATE TABLE " + DB_Table + " (" + key_rowid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + key_name + " TEXT NOT NULL, " + key_description + " TEXT NOT NULL, "
                + key_photo + " BLOG NOT NULL, " + key_price + " INTEGER NOT NULL, "
                + key_amount + " INTEGER NOT NULL, " + key_seller + " INTEGER NOT NULL);";
        DB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + DB_Table);
        onCreate(DB);
    }

    public boolean insert_Product(String p_name, String p_description, byte[] p_photo, int p_price, int p_amount, String sellerName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_name, p_name);
        contentValues.put(key_description, p_description);
        contentValues.put(key_photo, p_photo);
        contentValues.put(key_price, p_price);
        contentValues.put(key_amount, p_amount);
        contentValues.put(key_seller, sellerName);
        long insert_done = DB.insert(DB_Table, null, contentValues);
        if (insert_done == -1) { return false; }
        else { return true; }
    }

    public boolean update_Product(String P_id, String p_name, String p_description, byte[] p_photo, int p_price, int p_amount, String sellerName) {
        int updated_amount = p_amount-1;
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_name, p_name);
        contentValues.put(key_description, p_description);
        contentValues.put(key_photo, p_photo);
        contentValues.put(key_price, p_price);
        contentValues.put(key_amount, updated_amount);
        contentValues.put(key_seller, sellerName);
        long update_done = DB.update(DB_Table, contentValues, key_rowid +  "=?",  new String[]{P_id});
        if (update_done == -1) { return false; }
        else { return true;}
    }

    public boolean delete(String P_id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        long update_done = DB.delete(DB_Table, key_rowid +  "=?",  new String[]{P_id});

        if (update_done == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + DB_Table, null);
        return cursor;
    }

    public Cursor getProductOfUser (String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from " + DB_Table + " WHERE " + key_seller + " = '" + userName + "'" , null);
        return cursor;
    }

}

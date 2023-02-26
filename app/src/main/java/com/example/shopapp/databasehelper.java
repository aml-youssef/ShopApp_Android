package com.example.shopapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Vector;

public class databasehelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Souq.dp";
    public static final String TABLE_NAME="users";
    public static final String col1="username";
    public static final String col2="password";
    public Vector<String> id_user=new Vector<>(0);
    //public static int currentUserId;

    public databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +"("+ col1 +" TEXT PRIMARY KEY,"+ col2 +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME +"" );
        onCreate(db);
    }

    public boolean insertdata(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues conval=new ContentValues();
        conval.put(col1,username);
        conval.put(col2,password);
        long res = db.insert(TABLE_NAME,null,conval);
        if (res==-1)
            return false;
        else
            return true;
    }

    public boolean checkunique(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT "+ col1 +" FROM "+ TABLE_NAME +" WHERE "+ col1 +" = '"+username.trim()+"'",null);
        if (res.getCount()==0)
            return true;
        else
            return false;
    }

    public boolean checkhasaccount(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT " + col1 + "," + col2 + " FROM " + TABLE_NAME + " WHERE " + col1 + " = '" + username + "' AND "+ col2 +" = '" + password + "'",null);
        if (res.getCount()==0)
            return false;
        else
            return true;
    }
}

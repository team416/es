package com.example.androidtest;

/**
 * Created by 13786 on 2019/6/17.
 */
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteDatabase.CursorFactory;

import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;



public class DatabaseHelper extends SQLiteOpenHelper{

    //带全部参数的构造函数，此构造函数必不可少

    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        //创建数据库sql语句 并 执行
        db.execSQL("create table person(_id integer primary key autoincrement,Sno char(20),Password char(20),question char(20),answer char(20))");
        db.execSQL("create table message(_id integer primary key autoincrement, Sno char(20), time date)");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
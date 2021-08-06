package com.chaoxing.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyOpenHelper(@Nullable Context context) {
        super(context, "sqlLite2", null, 1);
        System.out.println("MyOpenHelper创建了");

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("数据库db创建了");
        db.execSQL("create table info(_id integer primary key autoincrement,  name varchar(20), phone varchar(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.chaoxing.listviewsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(@Nullable Context context) {
        super(context, "SqLiteListView", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varcahr(20),phone varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

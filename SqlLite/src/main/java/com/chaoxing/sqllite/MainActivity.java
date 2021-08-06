package com.chaoxing.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String name=null;
    String phone=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyOpenHelper myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();

        db.execSQL("insert into info(name,phone) values('张哥2','2225555')");

        System.out.println("开始查询");

        Cursor cursor = db.rawQuery("select * from info", null);

        while (cursor.moveToNext()){
             name = cursor.getString(1);
            phone = cursor.getString(cursor.getColumnIndex("phone"));

            System.out.println("name:"+name);
            System.out.println("phone:"+phone);
            Toast.makeText(this,"name:"+name+"\n phone:"+phone,Toast.LENGTH_LONG).show();
        }

        TextView viewById = (TextView)findViewById(R.id.tx_hello);
        viewById.setText("name:"+name+"\n phone:"+phone);
                System.out.println("关闭数据库db");
        db.close();
    }
}
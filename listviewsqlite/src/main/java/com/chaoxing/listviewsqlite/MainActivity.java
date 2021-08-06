package com.chaoxing.listviewsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chaoxing.listviewsqlite.bean.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;

    private static ArrayList<Person> persons = new ArrayList<>();

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.lv_listv);
        listview.setAdapter(new MyAdapter());
        myOpenHelper = new MyOpenHelper(this);


        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from info", null);
        while (cursor.moveToNext()) {

            Person person = new Person();
            person.name = cursor.getString(1);
            person.phone = cursor.getString(2);

            persons.add(person);

        }
        cursor.close();
        db.close();

        for (Person person : persons) {

            System.out.println(person);
        }


    }


    public void query(View view) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from info", null);
        while (cursor.moveToNext()) {

            Person person = new Person();
            person.name = cursor.getString(1);
            person.phone = cursor.getString(2);

            persons.add(person);

        }
        cursor.close();
        db.close();

        for (Person person : persons) {

            System.out.println(person);
        }
    }

    public void insert(View view) {
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        db.execSQL("insert into info(name,phone) values('王五','138888888')");
        db.execSQL("insert into info(name,phone) values('王六','138888888')");
        db.execSQL("insert into info(name,phone) values('王其','138888888')");
        db.execSQL("insert into info(name,phone) values('王吧','138888888')");
        db.execSQL("insert into info(name,phone) values('王就','138888888')");
        db.close();

    }

    public void clean(View view) {

        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
//        db.execSQL("delete  from SqLiteListView; ");
        db.delete("SqLiteListView", null, null);
        db.close();

    }

    private class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return persons.size();
//            return 6;
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("调用getView" + position);

            View itemView = null;
            if (convertView == null) {
//               itemView = findViewById(R.layout.item);

                itemView = View.inflate(MainActivity.this, R.layout.item, null);

            } else {
//                System.out.println("重用旧的对象"+position);
                itemView = convertView;

            }

            TextView name  = (TextView)itemView.findViewById(R.id.tv_name);
            TextView phone =(TextView)itemView.findViewById(R.id.tv_phone);

            name.setText(persons.get(position).name);
            name.setText(persons.get(position).phone);

            return itemView;
        }
    }
}
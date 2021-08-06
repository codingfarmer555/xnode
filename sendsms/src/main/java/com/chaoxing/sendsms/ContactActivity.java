package com.chaoxing.sendsms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chaoxing.sendsms.domain.Contact;

import java.util.ArrayList;

public class ContactActivity extends Activity {

    private ArrayList<Contact> contacts=new ArrayList<Contact>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ListView lv_contact=findViewById(R.id.lv_contact);


        for (int i = 0; i < 20; i++) {

            Contact contact = new Contact();
            contact.name="张三"+i;
            contact.phone="132455555"+i;

            contacts.add(contact);
        }
        Contact contact = new Contact();
        contact.name="张广贺";
        contact.phone="17603940501";
        contacts.add(contact);
        MyAdapter myAdapter = new MyAdapter();
        lv_contact.setAdapter(myAdapter);


        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contacts.get(position);
                Intent intent = new Intent();
                intent.putExtra("phone", contact.phone);
                setResult(2,intent);

                finish();

            }
        });
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Object getItem(int position) {
            return contacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=null;

            if (convertView==null){
                view = View.inflate(getApplicationContext(), R.layout.item_contact, null);

            }else{
                view=convertView;
            }

             TextView tv_name= view.findViewById(R.id.tv_name);
             TextView tv_phone= view.findViewById(R.id.tv_phone);

            Contact contact = contacts.get(position);

            tv_name.setText(contact.name);
            tv_phone.setText(contact.phone);

            return view;
        }
    }

}

package com.chaoxing.sendsms;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_number;
    private EditText et_content;
    private Button btn_contact;
    private Button btn_reply;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

        et_number = findViewById(R.id.et_phone);
        et_content = findViewById(R.id.et_smsContent);

        btn_contact = findViewById(R.id.btn_add);
        btn_reply = findViewById(R.id.btn_fastReply);
        btn_send = findViewById(R.id.btn_send);

        btn_contact.setOnClickListener(this);
        btn_reply.setOnClickListener(this);
        btn_send.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        System.out.println("返回数据");
        super.onActivityResult(requestCode, resultCode, data);


        if (data!=null) {

            switch (requestCode) {
                case 1:
                    System.out.println(data);
                    String phone = data.getStringExtra("phone");

                    et_number.setText(phone);
                    break;
                case 2:
                    System.out.println(data);
                    String reply = data.getStringExtra("reply");

                    et_content.setText(reply);
                    break;
                default:
                    break;
            }
        }


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {
            case R.id.btn_add:
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);

//                startActivity(intent);
                startActivityForResult(intent, 1);


                break;
            case R.id.btn_send:

                SmsManager smsManager = SmsManager.getDefault();

                String da = et_number.getText().toString().trim();
                String tx = et_content.getText().toString().trim();
                smsManager.sendTextMessage(da, null, tx, null, null);

                break;
            case R.id.btn_fastReply:
                Intent intent2 = new Intent(getApplicationContext(), ReplyActivity.class);
                startActivityForResult(intent2, 2);

                break;
        }


    }
}
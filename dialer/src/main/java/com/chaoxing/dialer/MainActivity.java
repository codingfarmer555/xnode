package com.chaoxing.dialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void dail(View v1) throws InterruptedException {

        View viewById = findViewById(R.id.button);

        EditText viewById1 = (EditText) findViewById(R.id.editTextTextPersonName);

        Editable text = viewById1.getText();
        String s = text.toString();

        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "写点东西吧", Toast.LENGTH_SHORT).show();
            System.out.println("用户输入为空");
        } else {
            System.out.println("打电话：" + s);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + s);
            intent.setData(data);


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//在这里面向系统请求权限,如果没有在这里面处理,不会执行下面的方法了
//这里就是向系统请求权限了,这里我还做了一个判断. sdk是M(M = 23 android L)才做这个请求,否则就不做.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 111);
                }
                return;
            }

            Toast.makeText(this, "准备拨号", Toast.LENGTH_SHORT).show();


            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {// handler接收到相关的消息后
//                        startActivity(intent);    // 打电话
                    }
                }
            };

            // 新建一个线程，过5秒钟后向handler发送一个消息
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.sendEmptyMessage(1);

                    startActivity(intent);    // 打电话

                }

            };

            Thread thread = new Thread(runnable);

            thread.start();

        }


    }
}
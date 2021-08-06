package com.chaoxing.sms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText et_url;
    private Button btn_show;
    private TextView tv_code;
    private TextView et_ncode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_url = findViewById(R.id.et_url);
        et_ncode = findViewById(R.id.et_ncode);
        btn_show = findViewById(R.id.btn_show);
        tv_code = findViewById(R.id.tv_code);


        btn_show.setOnClickListener(new MyListener());

    }


    public class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            new Thread(new Runnable(){
                @Override
                public void run() {

                    try {

                        String num = et_url.getText().toString().trim();
                        String cod = et_ncode.getText().toString().trim();

                        URL url = new URL("http://47.94.253.46:8081/sms/"+num+"/"+cod);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        //默认 默认get
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(10000);
//                    int responseCode = connection.getResponseCode();
//                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String result = Utils.getStringFromStream(inputStream);

                        tv_code.setText(result);
//                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }).start();


        }
    }
}
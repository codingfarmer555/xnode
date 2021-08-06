package com.chaoxing.asynchttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(MainActivity.this);


    }


    @Override
    public void onClick(View v) {

        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        AsyncHttpClient httpClient = new AsyncHttpClient();

        ResponseHandlerInterface responseHandler=new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println(statusCode);
                try {
                    String resp= new String(responseBody, "utf-8");
                    System.out.println(resp);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("失败了");
            }
        };
        httpClient.get("http://47.94.253.46", responseHandler);

        if (v.getId()== R.id.btn_login){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String username = et_username.getText().toString().trim();
                    String password = et_password.getText().toString().trim();





                }
            }).start();
        }
    }


}
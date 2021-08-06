package com.chaoxing.httpclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        btn_login.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
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
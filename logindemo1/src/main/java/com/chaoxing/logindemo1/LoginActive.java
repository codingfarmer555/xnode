package com.chaoxing.logindemo1;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActive extends AppCompatActivity {

    private EditText et_userName;
    private EditText et_password;
    private CheckBox cb_isSave;
    private Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载界面
        setContentView(R.layout.activity_login);

        //控件
        et_userName = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_isSave = (CheckBox) findViewById(R.id.cb_isSava);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new LoginBtnListener());

        String userName = Utils.getUserNameOrPassword(LoginActive.this, 0);
        String Password = Utils.getUserNameOrPassword(this, 1);

        et_userName.setText(userName);
        et_password.setText(Password);

        if (!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(Password)){
            cb_isSave.setChecked(true);
        }
    }


    private class LoginBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            //1,获取用户输入
            String password = et_password.getText().toString();
            String userName = et_userName.getText().toString();

            System.out.println(password);
            System.out.println(userName);

            //2，判断输入是否为空
            if(TextUtils.isEmpty(userName)|TextUtils.isEmpty(password)){
                //为空
                Toast.makeText(LoginActive.this,"用户名密码不能为空",Toast.LENGTH_SHORT).show();
            }else{

                //不为空，登入
                boolean checked = cb_isSave.isChecked();
                if (checked){
                    //保存用户名密码
                    boolean b = Utils.savaInfo(LoginActive.this, userName, password);

                    if (!b){
                        Toast.makeText(LoginActive.this,"保存失败",Toast.LENGTH_SHORT).show();

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(LoginActive.this,"保存成功",Toast.LENGTH_SHORT).show();
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    Log.d("Login","保存用户名："+userName+"   密码："+password);
                }else{
                    //清除帐密
                    Utils.savaInfo(LoginActive.this, "", "");

                }

                Log.d("Login","开始登录");

                Toast.makeText(LoginActive.this,"开始登录"+"\n保存用户名："+userName+"   密码："+password,new Integer(2)).show();


                LoginingView loginingView = new LoginingView(LoginActive.this);
//                loginingView.bitmapX= event.getX();
//                loginingView.bitmapY= event.getY();



//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(50, 50);
                RelativeLayout loginView = (RelativeLayout)findViewById(R.id.login);
//                loginingView.invalidate();
//                loginView.addView(loginingView);


                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 0; i <10; i++) {


                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                loginingView.setRotationY(loginView.getRotationY()+1);
                            }
                        }


                    }
                }).start();

                Handler handler1 = new Handler(){
                    @Override
                    public void handleMessage(Message msg){
                        if(msg.what == 2)  {// handler接收到相关的消息后
//                        startActivity(intent);    // 打电话

                        }
                    }
                };

//                viewById.addView(loginingView);
//                addContentView(loginingView,layoutParams);

                Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg){
                        if(msg.what == 1)  {// handler接收到相关的消息后
//                        startActivity(intent);    // 打电话
                            setContentView(R.layout.activity_studentmanager);
                        }
                    }
                };

//                 新建一个线程，过5秒钟后向handler发送一个消息
                Runnable runnable = new Runnable(){
                    public void run()
                    {

                        try {
                            Thread.sleep(2800);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(2);

                        try {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }

                        handler.sendEmptyMessage(1);
//                        setContentView(R.layout.activity_studentmanager);


                    }

                };

                Thread thread = new Thread(runnable);

                thread.start();


            }


        }
    }
}
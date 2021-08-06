package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading);

//        setContentView(R.layout.activity_main);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 1)  {// handler接收到相关的消息后
                    setContentView(R.layout.activity_load);    // 显示真正的应用界面
                }
            }
        };

        // 新建一个线程，过5秒钟后向handler发送一个消息
        Runnable runnable = new Runnable(){
            public void run()
            {
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(1);

            }

        };

        Thread thread = new Thread(runnable);

        thread.start();



        //                  // 下面是模拟数据处理需要5秒钟的时间

//                  try

//                  {

//                          Thread.sleep(5000);

//                  }

//                 catch (InterruptedException e)

//                  {

//                          e.printStackTrace();

//                  }

//

//                 setContentView(R.layout.main);

        long l = System.currentTimeMillis();

        while (true){
            if ((System.currentTimeMillis()-l)>=20){

//                setContentView(R.layout.activity_main);
                System.out.println(System.currentTimeMillis());
                break;
            }

        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("onStart-------");
    }



    int size=30;
    public void  smaller2(View v1){
         TextView b1;
         b1=findViewById(R.id.textView);
         b1.setTextSize(size--);
    }


    public void  bigger2(View v1){
        TextView b1;
        b1=findViewById(R.id.textView);
        b1.setTextSize(size++);
    }


    int flag=1;
    public void round(View v1){


            TextView b1;
            b1=findViewById(R.id.textView);
        if(flag==1){
            System.out.println("正着转");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    b1.setTranslationX(new Float(Math.random()*300));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    b1.setTranslationY(new Float(Math.random()*300));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    b1.setTranslationZ(new Float(Math.random()*300));
                }
                
        }else{

            System.out.println("反着转");

            for (int i = 0; i < 99999; i++) {
                b1=findViewById(R.id.textView);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    b1.setTranslationX(size--);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    b1.setTranslationY(size--);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    b1.setTranslationZ(size--);
                }

            }

            flag=1;
        }
        System.out.println("结束");

    }


    public void turnaround(View v1) {

        TextView b1;
        b1=findViewById(R.id.textView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            b1.setRotation(new Float(Math.random()*300));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            b1.setRotationX(new Float(Math.random()*300));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            b1.setRotationY(new Float(Math.random()*300));
        }


    }


    public void renew(View v1){


        new AlertDialog.Builder(MainActivity.this).setTitle("严重警告!")
                .setMessage("重置有风险，确定重置吗？")
                .setPositiveButton("确定",
                        (dialog,which)->{
                            TextView b1;
                            b1=findViewById(R.id.textView);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                b1.setRotation(0);
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                b1.setRotationX(0);
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                b1.setRotationY(0);
                            }
                        })
                .setNegativeButton("取消吧",(dialog,which)->{

        }).show();

    }

    public void toDemo1(View v1) {

        setContentView(R.layout.activity_main);

        Handler  handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 1)  {// handler接收到相关的消息后


                    System.out.println("正着转");

                    TextView b1;
                    b1=findViewById(R.id.textView);

//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                        b1.setTranslationX(new Float(Math.random()*300));
//                    }
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//                        b1.setTranslationY(new Float(Math.random()*300));
//                    }
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        b1.setTranslationZ(new Float(Math.random()*300));
//                    }
//


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        b1.setRotation(b1.getRotation()+1);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        b1.setRotationX(b1.getRotationX()+1);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        b1.setRotationY(b1.getRotationY()+1);
                    }


                }
            }
        };

        // 新建一个线程，过5秒钟后向handler发送一个消息
        Runnable runnable = new Runnable(){
            public void run() {

                for (int i = 0; i < 50000; i++) {
                    try {
                        Thread.sleep(40);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    handler.sendEmptyMessage(1);

                }


            }

        };

        Thread thread = new Thread(runnable);

        thread.start();


    }

    public void toPicture(View v1) {

        setContentView(R.layout.activity_picture);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.pic);

        for (int i = 0; i < 300; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(R.drawable.touxiang);
            imageView.setPadding(2,2,3,2);

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(90, 88);

            imageView.setLayoutParams(layoutParams);

            gridLayout.addView(imageView);
        }

    }

    public void toFollow(View v1) {

        setContentView(R.layout.activity_follow);

        FrameLayout viewById = (FrameLayout) findViewById(R.id.follow);

        FollowView followView = new FollowView(this);
        followView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               followView.bitmapX= event.getX();
               followView.bitmapY= event.getY();
               followView.invalidate();
                return true;
            }
        });

        viewById.addView(followView);

    }
}
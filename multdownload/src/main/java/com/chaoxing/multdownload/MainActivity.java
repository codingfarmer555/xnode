package com.chaoxing.multdownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_url;
    private EditText et_count;
    private Button btn_download;
    private LinearLayout ll_progress;
    private int threadCount = 3;
    private String path = "http://forservice.vip:8080/he/sbpengbke.jpg";
    private int blockSize;
    private Handler handler;
    Bitmap decodeFile = null;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_url = findViewById(R.id.et_username);
        et_count = findViewById(R.id.et_password);
        btn_download = findViewById(R.id.btn_login);

        ll_progress = findViewById(R.id.ll_progress);

        btn_download.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        file = new File(getFileName(path));
        if (file.exists()) {
            file.delete();
        }

        //重置数量
        ll_progress.removeAllViews();

//        ll_progress.getChildAt()

        String temp = et_count.getText().toString().trim();
         path = et_url.getText().toString().trim();
         threadCount = Integer.parseInt(temp);

        if (threadCount > 4) {
            threadCount = 4;
            Toast.makeText(this, "不建议超过四个线程哦", Toast.LENGTH_SHORT).show();
        }

        //生成进度条
        for (int i = 0; i < threadCount; i++) {
            View.inflate(getApplicationContext(), R.layout.item, ll_progress);
        }


        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    URL url = new URL(path);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(100000);
                    int responseCode = connection.getResponseCode();
                    System.out.println(responseCode);
                    int length = connection.getContentLength();
                    System.out.println(length);

                    RandomAccessFile file = new RandomAccessFile(
                            getFileName(path),
                            "rw");

                    file.setLength(length);

                    blockSize = length / threadCount;
                    for (int i = 0; i < threadCount; i++) {
                        int startIndex = i * blockSize;
                        int endIndex = (i + 1) * blockSize - 1;

                        //最后一个线程
                        if (i == threadCount - 1) {
                            endIndex = length - 1;
                        }


                        LinearLayout progressItem = (LinearLayout) ll_progress.getChildAt(i);
                        ProgressBar pb = (ProgressBar) progressItem.getChildAt(1);


                        //给进度条显示的最大进度
                        pb.setMax(endIndex - startIndex);

                        DownLoadThread downLoadThread = new DownLoadThread(startIndex, endIndex, i);
                        downLoadThread.start();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        LinearLayout inflate = (LinearLayout) View.inflate(getApplicationContext(), R.layout.imagev, ll_progress);
        ImageView childAt = (ImageView) inflate.getChildAt(threadCount);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {// handler接收到相关的消息后
//                    setContentView(R.layout.activity_load);    // 显示真正的应用界面
                    childAt.setImageBitmap(decodeFile);
                }
            }
        };

        System.out.println(inflate);

        new Thread() {
            @Override
            public void run() {
                while (true) {

//                    RandomAccessFile file = new RandomAccessFile(getFileName(
//                            path),
//                            "rw");

                    decodeFile = BitmapFactory.decodeFile(getFileName(path));

//                    Message msg = new Message();/
//                    Bundle bun = new Bundle();/
//                        bun.putByteArray("file",fil);
//                    msg.setData(bun);
//                        handler.sendMessage(1)
                         handler.sendEmptyMessage(1);
//                         childAt.setImageBitmap(decodeFile);

                }

            }
        }.start();


    }


    private class DownLoadThread extends Thread {

        private int startIndex;
        private int endIndex;
        private int threadId;
        private ProgressBar pb;

        public DownLoadThread(int startIndex, int endIndex, int threadId) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
            LinearLayout progressItem = (LinearLayout) ll_progress.getChildAt(threadId);
            this.pb = (ProgressBar) progressItem.getChildAt(1);

//            pb.setMax();
        }


        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(100000);
                urlConnection.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);

                System.out.println("响应码=" + urlConnection.getResponseCode() + "---线程"
                        + threadId
                        + "--开始下载"
                        + startIndex);


                InputStream ips = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(ips);
                int len = -1;
                byte[] buffer = new byte[1024 * 500];
                RandomAccessFile file = new RandomAccessFile(getFileName(
                        path),
                        "rw");

                file.seek(startIndex);

                int pos = 0;
                while ((len = bis.read(buffer)) != -1) {
                    System.out.println("---线程"
                            + threadId
                            + "--正在下载---len="
                            + len + "---进度：" + (pos = pos + len));
                    file.write(buffer, 0, len);

                    //改变进度条
//                    System.out.println("线程:"+threadId+"---   变化了----------------------------------------------------------");
                    pb.setProgress(pos - 10000);

                }
                file.close();

                System.out.println("线程" + threadId + "下载----------------结束---------------");
                handler.sendEmptyMessage(1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileName(String s) {
        String[] strings = s.split("/");

//        String absolutePath = this.getCacheDir().getAbsolutePath();
        String absolutePath = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
            absolutePath = this.getExternalFilesDir(null).getAbsolutePath();
        }

        return absolutePath + "/" + strings[strings.length - 1];
    }
}
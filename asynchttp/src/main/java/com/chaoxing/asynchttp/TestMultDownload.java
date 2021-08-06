package com.chaoxing.asynchttp;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestMultDownload {
    public static void main(String[] args) {

        try {
            URL url = new URL("http://forservice.vip:8080/he/sbpengbke.jpg");

            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(100000);
            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);
            int length = connection.getContentLength();
            System.out.println(length);

            RandomAccessFile file=new RandomAccessFile(
                    getFileName("http://forservice.vip:8080/he/sbpengbke.jpg"),
                    "rw");

            file.setLength(length);

            int blockSize=length/3;
            for (int i = 0; i < 3; i++) {
                int startIndex=i*blockSize;
                int endIndex=(i+1)*blockSize-1;
                if (i==2){
                    endIndex=length-1;
                }

                DownLoadThread downLoadThread = new DownLoadThread(startIndex, endIndex, i);
                downLoadThread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class DownLoadThread extends Thread{

        private int startIndex;
        private int endIndex;
        private int threadId;

        public DownLoadThread(int startIndex, int endIndex, int threadId) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }


        @Override
        public void run() {
            try {
                URL url = new URL("http://forservice.vip:8080/he/sbpengbke.jpg");
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(100000);
                urlConnection.setRequestProperty("Range","bytes="+startIndex+"-"+endIndex);

                System.out.println("响应码="+urlConnection.getResponseCode()+"---线程"
                        +threadId
                        +"--开始下载"
                        +startIndex);



                InputStream ips = urlConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(ips);
                int len=-1;
                byte[] buffer=new byte[1024*500];
                RandomAccessFile file = new RandomAccessFile(getFileName(
                        "http://forservice.vip:8080/he/sbpengbke.jpg"),
                        "rw");

                file.seek(startIndex);

                int pos=0;
                while ((len=bis.read(buffer))!=-1){
                    System.out.println("---线程"
                            +threadId
                            +"--正在下载---len="
                            +len+"---进度："+(pos+=len));
                    file.write(buffer,0,len);
                }
                file.close();

                System.out.println("线程"+threadId+"下载----------------结束---------------");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFileName(String s) {
        String[] strings = s.split("/");
        return strings[strings.length-1];
    }
}

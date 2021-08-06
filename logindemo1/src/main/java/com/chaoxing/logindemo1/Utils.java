package com.chaoxing.logindemo1;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    private static String[] strings;

    public static boolean savaInfo(Context context, String userName, String password) {

        String info=userName+"##"+password;

//        File filesDir = context.getExternalFilesDir(null);
        File filesDir = context.getFilesDir();
        File file=new File(filesDir.getAbsolutePath()+"/info.txt");
//        File file=new File("loginInfo.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(info.getBytes());
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUserNameOrPassword(Context context, int i) {

        File filesDir = context.getFilesDir();
        File file=new File(filesDir.getAbsolutePath()+"/info.txt");


        byte[] b=null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String s = bufferedReader.readLine();

            strings = s.split("##");

            if (TextUtils.isEmpty(s)||strings.length==0||strings.length==1){

                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings[i] ;

    }

    public static void getPassword(Context context) {
        File filesDir = context.getFilesDir();
        File file=new File(filesDir.getAbsolutePath()+"/info.txt");
    }
}

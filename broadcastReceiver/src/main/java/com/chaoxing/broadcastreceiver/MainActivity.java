package com.chaoxing.broadcastreceiver;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS} , 1);
    }
}
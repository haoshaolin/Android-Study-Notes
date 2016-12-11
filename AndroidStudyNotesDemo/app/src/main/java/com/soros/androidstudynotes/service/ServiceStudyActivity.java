package com.soros.androidstudynotes.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.soros.androidstudynotes.R;

public class ServiceStudyActivity extends AppCompatActivity {
    private final String TAG = ServiceStudyActivity.class.getSimpleName();

    private Handler mHander;

    int cmd = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        Log.d(TAG, "==============onCreate==thread name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId());



        mHander = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "==============handleMessage==thread name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId());
            }
        };

        new Thread() {
            @Override
            public void run() {
                super.run();
                mHander.sendEmptyMessage(0);
            }
        }.start();


    }

    public void onStartService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainService.class);
        intent.putExtra("cmd",cmd);
        startService(intent);

        cmd = (cmd + 1) % 2;
    }

    public void onStopService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainService.class);
        stopService(intent);
    }

    public void onBindService(View view) {

    }

    public void onUnbindService(View view) {

    }



}

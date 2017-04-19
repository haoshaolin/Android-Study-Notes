package com.soros.androidstudynotes.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.soros.androidstudynotes.R;
import com.soros.androidstudynotes.service.ServiceStudyActivity;

public class HandlerStudyActivity extends AppCompatActivity {
    private final String TAG = HandlerStudyActivity.class.getSimpleName();
    private Handler mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_study);
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
}

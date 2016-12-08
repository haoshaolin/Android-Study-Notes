package com.soros.androidstudynotes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.soros.androidstudynotes.R;

public class SingleInstanceActivity extends AppCompatActivity {

    private final String TAG = SingleInstanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "==============onCreate=============");
        setContentView(R.layout.activity_single_instance);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "==============onNewIntent=============");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "==============onRestart()=============");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "==============onStart()=============");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "==============onResume()=============");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "==============onPause()=============");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "==============onStop()=============");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "==============onDestroy()=============");
    }

    public void onStartStandard(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    public void onStartSingleTop(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SingleTopActivity.class);
        startActivity(intent);
    }

    public void onStartSingleInstance(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SingleInstanceActivity.class);
        startActivity(intent);
    }

    public void onStartSingleTask(View view) {
        Intent intent = new Intent();
        intent.setClass(this, SingleTaskActivity.class);
        startActivity(intent);
    }
}

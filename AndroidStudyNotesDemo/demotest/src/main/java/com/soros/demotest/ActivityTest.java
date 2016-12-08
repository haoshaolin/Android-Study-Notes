package com.soros.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void onStartStandard(View view) {
        Intent intent = new Intent("study.intent.action.STANDARDD_ACTIVITY");
        startActivity(intent);
    }

    public void onStartSingleTop(View view) {
        Intent intent = new Intent("study.intent.action.SINGLETOP_ACTIVITY");
        startActivity(intent);
    }

    public void onStartSingleInstance(View view) {
        Intent intent = new Intent("study.intent.action.SINGLEINSTANCE_ACTIVITY");
        startActivity(intent);
    }

    public void onStartSingleTask(View view) {
        Intent intent = new Intent("study.intent.action.SINGLETASK_ACTIVITY");
        startActivity(intent);
    }
}

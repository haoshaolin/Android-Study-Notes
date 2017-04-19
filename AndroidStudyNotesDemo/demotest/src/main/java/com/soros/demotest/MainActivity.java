package com.soros.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onActivityTest(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ActivityTest.class);
        startActivity(intent);
    }

    public void serviceTest(View view) {
        Intent intent = new Intent();
        intent.setClass(this, ServiceTestActivity.class);
        startActivity(intent);
    }

}

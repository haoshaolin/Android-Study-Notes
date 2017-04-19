package com.soros.androidstudynotes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.soros.androidstudynotes.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Android生命周期演示
 */
public class LifeCycleActivity extends AppCompatActivity {

    private final String TAG = LifeCycleActivity.class.getSimpleName();

    private String mStrData;
    private Thread mTestThread;
    class BigData {
        Thread thread;
        String strData;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "==============onSaveInstanceState()=============");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "==============onRestoreInstanceState()=============");
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

    private BigData getBigData() {
        BigData bigData = new BigData();
        bigData.strData = mStrData;
        bigData.thread = mTestThread;
        return bigData;
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Log.d(TAG, "==============onRetainCustomNonConfigurationInstance()=============");
        return getBigData();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "==============onCreate()=============");
        setContentView(R.layout.activity_life_cycle);

        final BigData bigData = (BigData) getLastCustomNonConfigurationInstance();
        if(bigData == null) {
            Random random = new Random(1000);
            mStrData = "I am Big Data " + random.nextInt(9999);
            mTestThread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(30000);
                        Log.d(TAG, "==================Thread is waked.===================");
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.d(TAG, "==================Thread is interrupted.======================");
                    }
                }
            };
            mTestThread.start();
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
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

    FilenameFilter filenameFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    };
    /**
     *  查找方法
     * @param path
     */
    public List search(String path){
        File f=new File(path+"\\");
        List results = new ArrayList();
        File[] files = f.listFiles(filenameFilter);
        if(files != null){
            for(int i=0;i<files.length;i++){
                File sf=files[i];
                //如果sf是文件夹则继续查找
                if(sf.isDirectory()){
                    search(sf.getPath());
                }else{
                    results.add(sf.getAbsolutePath());
                    //如果符合过滤条件则输到文件中
                    /*if(filter.accept(sf)){
                        fileSet.add(sf);
                        pw.println(sf.getPath());
                        pw.flush();
                    }*/
                }
            }
        }
        return results;
    }
}

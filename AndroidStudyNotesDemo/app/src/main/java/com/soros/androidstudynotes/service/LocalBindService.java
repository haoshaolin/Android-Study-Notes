package com.soros.androidstudynotes.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class LocalBindService extends Service {

    private final Random mGenerator = new Random();
    public class LocalBinder extends Binder {
        LocalBindService getService() {
            return LocalBindService.this;
        }
    }


    private final String TAG = LocalBindService.class.getSimpleName();
    private IBinder mBinder = new LocalBinder();

    public LocalBindService() {
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "=============onBind==========");
        return mBinder;
    }

    /**
     * 如果您的服务已启动并接受绑定，则当系统调用您的 onUnbind() 方法时，
     * 如果您想在客户端下一次绑定到服务时接收 onRebind() 调用，则可选择
     * 返回 true
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "=============onCreate==========");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "=============onRebind==========");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "=============onDestroy==========");
    }
}

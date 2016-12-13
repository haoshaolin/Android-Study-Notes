package com.soros.androidstudynotes.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

public class LifeCycleService extends Service {

    public class LifeCycleBinder extends Binder {
        LifeCycleService getService() {
            return LifeCycleService.this;
        }
    }

    private class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {

            Log.d(TAG, "==========handleMessage=======what=" + msg.what + ",arg1=" + msg.arg1);
            Log.d(TAG, "==============handleMessage==thread name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId());
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                Log.d(TAG, "==========InterruptedException========");
            }

            stopSelf(msg.arg1);
        }
    }

    private final String TAG = LifeCycleService.class.getSimpleName();
    private IBinder mBinder = new LifeCycleBinder();
    private ServiceHandler mServiceHandler;
    private HandlerThread mHandlerThread;

    public LifeCycleService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "=============onBind============");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "=============onUnbind============");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "=============onRebind============");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "=============onCreate============");
//        Log.d(TAG, "==============onCreate==thread name=" + Thread.currentThread().getName() + ", id=" + Thread.currentThread().getId());
//        mHandlerThread = new HandlerThread("MainServiceTaskThread", Process.THREAD_PRIORITY_BACKGROUND);
//        mHandlerThread.start();
//
//        mServiceHandler = new ServiceHandler(mHandlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "=============onStartCommand============");
//        if (intent != null) {
//            int cmd = intent.getIntExtra("cmd", -1);
//            Message msg = mServiceHandler.obtainMessage();
//            msg.arg1 = startId;
//            switch (cmd) {
//                case 0:
//                    msg.what = 0;
//                    break;
//                case 1:
//                    msg.what = 1;
//                    break;
//                default:
//                    break;
//            }
//            mServiceHandler.sendMessage(msg);
//        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "=============onDestroy============");
        super.onDestroy();
//        mHandlerThread.getLooper().quit();
//        mHandlerThread.interrupt();
    }
}

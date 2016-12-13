package com.soros.androidstudynotes.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LocalStartService extends Service {

    final String TAG = LocalStartService.class.getSimpleName();
    public LocalStartService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "==========onCreate=======");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "==========onStartCommand=======");
        if(intent != null) {
            int cmd = intent.getIntExtra("cmd", 0);
            switch (cmd) {
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "==========onDestroy=======");
    }
}

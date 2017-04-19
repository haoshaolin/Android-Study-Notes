package com.soros.androidstudynotes.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.soros.model.Person;

public class RemoteService extends Service {

    private final String TAG = RemoteService.class.getSimpleName();
    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public void threadSleep() throws RemoteException {
            Log.d(TAG, "====================threadSleep(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d(TAG, "====================add(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
            return a + b;
        }

        @Override
        public void getPerson(String name) throws RemoteException {
            Person person = new Person();
            person.setName(name);
            person.setAge(12);
        }
    };


    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "====================onBind(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "====================onDestroy(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
        super.onDestroy();
    }
}

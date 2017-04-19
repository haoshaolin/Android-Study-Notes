package com.soros.demotest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.soros.androidstudynotes.service.IRemoteService;

public class ServiceTestActivity extends AppCompatActivity {

    final String TAG = ServiceTestActivity.class.getSimpleName();


    /** Messenger for communicating with the service. */
    Messenger mMessengerService = null;
    private ServiceConnection mMessengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "=========MessengerService onServiceConnected=========");
            mMessengerService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "=========MessengerService onServiceDisconnected=========");
            mMessengerService = null;
        }
    };

    Messenger mClientMessenger = new Messenger(new ClientHandler());

     class ClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "=====111111111===handleMessage======");
            switch (msg.what) {
                case 2:
                    Log.d(TAG, "========handleMessage======" + msg.obj);
//                    Bundle data = (Bundle) msg.obj;
                    Snackbar.make(findViewById(R.id.callMessenger), "Client received message : " /*+ data.getString("data")*/, Snackbar.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    /*******************************
     *
     *  远程Service
     *
     ******************************/
    private IRemoteService mRemoteService;
    private ServiceConnection mRemoteServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteService = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindMessengerService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindMessengerService();
    }

    public void callRemoteMessengerService(View view) {
        if(mMessengerService != null) {
            Message msg = Message.obtain(null, 1, 0, 0);
            msg.replyTo = mClientMessenger;
            try {
                mMessengerService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Snackbar.make(view, "MessengerService is not bound!", Snackbar.LENGTH_SHORT).show();
        }
    }

    public void bindMessengerService() {
        if(mMessengerService == null) {
            Intent intent = new Intent("com.soros.study.action.MESSENGER_SERVICE");
            intent.setPackage("com.soros.androidstudynotes");
            bindService(intent, mMessengerServiceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    public void unbindMessengerService() {
        if (mMessengerService != null) {
            unbindService(mMessengerServiceConnection);
        }
    }

    public void bindRemoteService(View view) {
        if(mRemoteService == null) {
            Intent service = new Intent("com.soros.study.action.REMOTE_SERVICE");
            service.setPackage("com.soros.androidstudynotes");
            boolean bindResult = bindService(service, mRemoteServiceConnection, Service.BIND_AUTO_CREATE);
            Log.d(TAG, "bindRemoteService : result=" + bindResult);
        } else {
            Snackbar.make(view, "RemoteService is bound!", Snackbar.LENGTH_LONG).show();
        }
    }

    public void unbindRemoteService(View view) {
        if(mRemoteService != null) {
            unbindService(mRemoteServiceConnection);
            mRemoteService = null;
        }
    }

    public void add(View view) {
        if(mRemoteService != null) {
            try {
                int result = mRemoteService.add(12, 10);
                Log.d(TAG, "add result = " + result);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void syncCallThreadSleep(View view) {
        if(mRemoteService != null) {
            try {
                Log.d(TAG, "===================syncCallThreadSleep(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
                mRemoteService.threadSleep();
                Log.d(TAG, "===================syncCallThreadSleep()===============end====");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void asyncCallThreadSleep(View view) {
        if(mRemoteService != null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "===================asyncCallThreadSleep(): thread id=" + Thread.currentThread().getId() + ", name=" + Thread.currentThread().getName());
                        mRemoteService.threadSleep();
                        Log.d(TAG, "===================asyncCallThreadSleep()============end=======");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}

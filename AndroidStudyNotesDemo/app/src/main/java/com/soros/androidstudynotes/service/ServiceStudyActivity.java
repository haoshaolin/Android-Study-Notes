package com.soros.androidstudynotes.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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

import com.soros.androidstudynotes.R;
import com.soros.model.MessengerData;

public class ServiceStudyActivity extends AppCompatActivity {
    private final String TAG = ServiceStudyActivity.class.getSimpleName();
    int cmd = 0;

    LocalBindService mLocalBindService;
    boolean mBindServiceBound = false;
    private ServiceConnection mBindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "=========LocalBindService onServiceConnected=========");
            LocalBindService.LocalBinder binder = (LocalBindService.LocalBinder) service;
            mLocalBindService = binder.getService();
            mBindServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "=========LocalBindService onServiceDisconnected=========");
            mBindServiceBound = false;
        }
    };

    LifeCycleService mLifeCycleService;
    boolean mLifeCycleServiceBound = false;
    private ServiceConnection mLifeCycleConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "=========LifeCycleService onServiceConnected=========");
            LifeCycleService.LifeCycleBinder binder = (LifeCycleService.LifeCycleBinder) service;
            mLifeCycleService = binder.getService();
            mLifeCycleServiceBound = true;
        }

        /**
         * Android 系统会在与服务的连接意外中断时（例如当服务崩溃或被终止时）调用该方法。
         * 当客户端取消绑定时，系统“不会”调用该方法。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "=========LifeCycleService onServiceDisconnected=========");
            mLifeCycleServiceBound = false;
        }
    };

    /** Messenger for communicating with the service. */
    Messenger mMessengerService = null;
    boolean mMessengerServiceBound = false;
    private ServiceConnection mMessengerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "=========MessengerService onServiceConnected=========");
            mMessengerService = new Messenger(service);
            mMessengerServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "=========MessengerService onServiceDisconnected=========");
            mMessengerServiceBound = false;
        }
    };
    Messenger mClientMessenger = new Messenger(new ClientHandler());
    class ClientHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerService.MSG_SAY_HELLO_REPLY:
//                    MessengerData messengerData = (MessengerData) msg.obj;
                    Snackbar.make(findViewById(R.id.callMessengerService), "Client received msg : " /*+ messengerData.data*/, Snackbar.LENGTH_LONG).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_study);
    }

    @Override
    public void onStart() {
        super.onStart();
        bindService(new Intent(this, MessengerService.class), mMessengerServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBindServiceBound) {
            unbindService(mBindConnection);
            mBindServiceBound = false;
        }

        if (mLifeCycleServiceBound) {
            unbindService(mLifeCycleConnection);
            mLifeCycleServiceBound = false;
        }

        if (mMessengerServiceBound) {
            unbindService(mMessengerServiceConnection);
            mMessengerServiceBound = false;
        }
    }


    public void onStartService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LocalStartService.class);
        intent.putExtra("cmd", cmd);
        startService(intent);

        cmd = (cmd + 1) % 2;
    }

    public void onStopService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LocalStartService.class);
        stopService(intent);
    }

    public void onBindService(View view) {
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent(this, LocalBindService.class);
        bindService(intent, mBindConnection, Context.BIND_AUTO_CREATE);
    }


    public void onUnbindService(View view) {
        if (mBindServiceBound) {
            unbindService(mBindConnection);
            mBindServiceBound = false;
        }
    }

    public void onCallServiceMethod(final View view) {
        if (mBindServiceBound) {
            int result = mLocalBindService.getRandomNumber();
            Snackbar.make(view, "RandomNumber is " + result, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, "Service is not bound, bind it?", Snackbar.LENGTH_SHORT).setAction("Bind", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bindService();
                }
            }).show();
        }
    }

    /**
     * 向MessageService发送消息
     * 远程调用参见demotest
     * @param view
     */
    public void sayHello(View view) {
        if(mMessengerServiceBound) {
            Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
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

    public void onStartLifeCycleService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LifeCycleService.class);
        startService(intent);
    }

    public void onStopLifeCycleService(View view) {
        Intent intent = new Intent();
        intent.setClass(this, LifeCycleService.class);
        stopService(intent);
    }

    public void onBindLifeCycleService(View view) {
        Intent intent = new Intent(this, LifeCycleService.class);
        bindService(intent, mLifeCycleConnection, Context.BIND_AUTO_CREATE);
    }

    public void onUnbindLifeCycleService(View view) {
        if (mLifeCycleServiceBound) {
            unbindService(mLifeCycleConnection);
            mLifeCycleServiceBound = false;
        }
    }

    public void onStartIntentServiceFoo(View view) {
        Intent intent = new Intent(StudyIntentService.ACTION_FOO);
        intent.setClass(this, StudyIntentService.class);
        intent.putExtra(StudyIntentService.EXTRA_PARAM1, "parm1");
        intent.putExtra(StudyIntentService.EXTRA_PARAM2, "parm2");
        startService(intent);
    }

    public void onStartIntentServiceBaz(View view) {
        Intent intent = new Intent(StudyIntentService.ACTION_BAZ);
        intent.setClass(this, StudyIntentService.class);
        intent.putExtra(StudyIntentService.EXTRA_PARAM1, "parm1");
        intent.putExtra(StudyIntentService.EXTRA_PARAM2, "parm2");
        startService(intent);
    }

}

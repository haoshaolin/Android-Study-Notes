package com.soros.androidstudynotes.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.soros.androidstudynotes.R;

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

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "=========LifeCycleService onServiceDisconnected=========");
            mLifeCycleServiceBound = false;
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_study);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        if (mBindServiceBound) {
            unbindService(mBindConnection);
            mBindServiceBound = false;
        }

        if (mLifeCycleServiceBound) {
            unbindService(mLifeCycleConnection);
            mLifeCycleServiceBound = false;
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ServiceStudy Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
}

package com.soros.androidstudynotes.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.soros.model.MessengerData;

public class MessengerService extends Service {

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    new AsyncTask(msg.replyTo).start();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    class AsyncTask extends Thread {
        Messenger clientMessenger;
        public AsyncTask(Messenger messenger) {
            clientMessenger = messenger;
        }

        @Override
        public void run() {
            try {
                sleep(5000);
                Log.d(TAG, "Task is over, reply to client!");
                MessengerData messengerData = new MessengerData();
                messengerData.data = "I am received you request, and reply to you!";
                Bundle bundle = new Bundle();
                bundle.putString("data", "I am received you request, and reply to you!");
                Message message = Message.obtain(null, MSG_SAY_HELLO_REPLY,  null);
                clientMessenger.send(message);
                Log.d(TAG, "Task is over, reply to client=========end=");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /** Command to the service to display a message */
    static final int MSG_SAY_HELLO = 1;
    static final int MSG_SAY_HELLO_REPLY = 2;

    final String TAG = MessengerService.class.getSimpleName();

    public MessengerService() {
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "=========onBind========");
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "=========onUnbind========");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "=========onDestroy========");
    }
}

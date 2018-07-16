package com.sserdiuk.androidprincipies;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class BroadCastSenderService extends Service {
    private String LOG_TAG = null;
    private ArrayList<String> mList;

    public BroadCastSenderService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LOG_TAG = this.getClass().getSimpleName();
        Log.i(LOG_TAG, "In onCreate");
        mList = new ArrayList<String>();
        mList.add("Object 1");
        mList.add("Object 2");
        mList.add("Object 3");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(BroadCastReceiverExampleActivity.mBroadcastStringAction);
                broadcastIntent.putExtra("Data", "Broadcast Data");
                sendBroadcast(broadcastIntent);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                broadcastIntent.setAction(BroadCastReceiverExampleActivity.mBroadcastIntegerAction);
                broadcastIntent.putExtra("Data", 10);
                sendBroadcast(broadcastIntent);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                broadcastIntent
                        .setAction(BroadCastReceiverExampleActivity.mBroadcastArrayListAction);
                broadcastIntent.putExtra("Data", mList);
                sendBroadcast(broadcastIntent);
            }
        }).start();

        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Wont be called as service is not bound
        Log.i(LOG_TAG, "In onBind");
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(LOG_TAG, "In onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
    }
}

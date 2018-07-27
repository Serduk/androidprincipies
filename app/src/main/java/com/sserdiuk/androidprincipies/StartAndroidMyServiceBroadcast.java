package com.sserdiuk.androidprincipies;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StartAndroidMyServiceBroadcast extends Service {
    final String TAG = StartAndroidMyServiceBroadcast.class.getSimpleName();
    ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        int time = intent.getIntExtra(StartAndroidServicesActivityExamples.PARAM_TIME, 1);
        int task = intent.getIntExtra(StartAndroidServicesActivityExamples.PARAM_TASK, 0);

        MyRun mr = new MyRun(startId, time, task);
        executorService.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        int task;

        MyRun(int time, int startId, int task) {
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(TAG, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Intent intent = new Intent(StartAndroidServicesActivityExamples.BROADCAST_ACTION);
            Log.d(TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                intent.putExtra(StartAndroidServicesActivityExamples.PARAM_TASK, task);
                intent.putExtra(StartAndroidServicesActivityExamples.PARAM_STATUS,
                        StartAndroidServicesActivityExamples.STATUS_START);

                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(time);

                intent.putExtra(StartAndroidServicesActivityExamples.PARAM_STATUS, StartAndroidServicesActivityExamples.STATUS_FINISH);
                intent.putExtra(StartAndroidServicesActivityExamples.PARAM_RESULT, time * 100);
                sendBroadcast(intent);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId  + ") = " + stopSelfResult(startId));
        }
    }
}

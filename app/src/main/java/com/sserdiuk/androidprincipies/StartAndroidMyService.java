package com.sserdiuk.androidprincipies;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StartAndroidMyService extends Service {
    final private String TAG = StartAndroidMyService.class.getSimpleName();

    ExecutorService executorService;

    public StartAndroidMyService() {
    }

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

        int time = intent.getIntExtra(StartAndroidServicesActivityExamples.PARAM_PINTENT, 1);
        PendingIntent pi = intent.getParcelableExtra(StartAndroidServicesActivityExamples.PARAM_PINTENT);

        MyRun mr = new MyRun(time, startId, pi);
        executorService.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyRun implements Runnable {
        int time;
        int startId;
        PendingIntent pi;

        MyRun(int time, int startId, PendingIntent pi) {
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(TAG, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                pi.send(StartAndroidServicesActivityExamples.STATUS_START);

                TimeUnit.SECONDS.sleep(time);

                Intent intent = new Intent().putExtra(StartAndroidServicesActivityExamples.PARAM_RESULT, time * 100);
                pi.send(StartAndroidMyService.this, StartAndroidServicesActivityExamples.STATUS_FINISH, intent);
            } catch (InterruptedException | PendingIntent.CanceledException ex) {
                ex.printStackTrace();
            }
            stop();
        }


        void stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId  + ") = " + stopSelfResult(startId));
        }
    }
}

package com.sserdiuk.androidprincipies;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class StartAndroidLocalBinding extends Service {
    private final static String TAG = StartAndroidLocalBinding.class.getSimpleName();
    MyBinder binder;

    Timer timer;
    TimerTask timerTask;
    long interval = 1000;

    public StartAndroidLocalBinding() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");

        timer = new Timer();
        schedule();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return binder;
    }

    void schedule() {
        if (timerTask != null) timerTask.cancel();

        if (interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "Timer Task Run");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        interval += gap;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval -= gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    class MyBinder extends Binder {
        StartAndroidLocalBinding getService() {
            return StartAndroidLocalBinding.this;
        }
    }
}

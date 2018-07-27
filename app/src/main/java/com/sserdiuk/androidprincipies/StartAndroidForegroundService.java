package com.sserdiuk.androidprincipies;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class StartAndroidForegroundService extends IntentService {
    public final static String TAG = StartAndroidForegroundService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public StartAndroidForegroundService() {
        super("StartAndroidForegroundService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("label");
        Log.d(TAG, "onHandleIntent start " + label);

        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.d(TAG, "onHandleIntent END" + label);
//        startForeground();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}

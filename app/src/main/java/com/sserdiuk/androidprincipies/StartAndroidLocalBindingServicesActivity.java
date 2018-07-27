package com.sserdiuk.androidprincipies;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartAndroidLocalBindingServicesActivity extends AppCompatActivity {

    final private static String TAG = StartAndroidLocalBindingServicesActivity.class.getSimpleName();

    boolean bound = false;
    ServiceConnection serviceConnection;
    Intent intent;
    StartAndroidLocalBinding localBinding;
    TextView tvInterval;
    long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_android_local_binding_services);

        tvInterval = findViewById(R.id.textView4);
        intent = new Intent(this, StartAndroidLocalBinding.class);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "MainActivity onServiceConnected");
                localBinding = ((StartAndroidLocalBinding.MyBinder) service).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "MainActivity onServiceDisconected");
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    public void onDown(View view) {
        if (!bound) return;
        interval = localBinding.downInterval(500);
        tvInterval.setText("interval is " + interval);
    }

    public void onUp(View view) {
        if (!bound) return;
        interval = localBinding.upInterval(500);
        tvInterval.setText("interval is " + interval);

    }

    public void onClickStart(View view) {
        startService(intent);
    }

    public void onStopServiceBinding(View view) {
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }
}

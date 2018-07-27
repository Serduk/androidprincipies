package com.sserdiuk.androidprincipies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class StartAndroidForegroundServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_android_foreground_service);
    }

    public void onClickStartForeground(View view) {
        startService(new Intent(this,
                StartAndroidForegroundService.class)
                .putExtra("time", 3)
                .putExtra("label", "cal 1"));

        startService(new Intent(this,
                StartAndroidForegroundService.class)
                .putExtra("time", 4)
                .putExtra("label", "cal 2"));

        startService(new Intent(this,
                StartAndroidForegroundService.class)
                .putExtra("time", 5)
                .putExtra("label", "cal 3"));
    }
}

package com.sserdiuk.androidprincipies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onFragmentClick(View view) {
        startActivity(new Intent(this, ForFragmentsTestingActivity.class));
    }

    public void onServiceClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
                startIntent.setAction(Constans.ACTION.STARTFOREGROUND_ACTION);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(MainActivity.this, ForegroundService.class);
                stopIntent.setAction(Constans.ACTION.STOPFOREGROUND_ACTION);
                startService(stopIntent);
                break;
            default:
                break;
        }
    }

    public void onBroadcastClick(View view) {
        startActivity(new Intent(this, BroadCastReceiverExampleActivity.class));
    }

    public void onBindingServiceClick(View view) {
        startActivity(new Intent(this, BoundServiceCheckActivity.class));
    }
}

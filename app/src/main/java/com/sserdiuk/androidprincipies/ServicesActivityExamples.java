package com.sserdiuk.androidprincipies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServicesActivityExamples extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_examples);
    }

    public void onStartService(View view) {
        startService(new Intent(this, MyServiceStartAndroidLessons.class));
    }

    public void onStopService(View view) {
        stopService(new Intent(this, MyServiceStartAndroidLessons.class));
    }
}

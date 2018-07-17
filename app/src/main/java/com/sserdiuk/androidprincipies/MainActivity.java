package com.sserdiuk.androidprincipies;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements TestFragment.OnFragmentInteractionListener {
    FragmentTransaction ft;

    TestFragment testFragment;
    TestFragmentTwo testFragmentTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testFragment = getFragmentManager().findFragmentById(R.id.fragment_container);

        ft  = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_placeholder, testFragment);
        ft.commit();

    }

    public void onFragmentClick(View view) {
        testFragment = new TestFragment();
        testFragmentTwo = new TestFragmentTwo();

        ft.addToBackStack(null);
//        ft.remove(fragment);
        ft.replace(R.id.fragment_placeholder, testFragmentTwo);
//        ft.add(fragment, "TAG1");
        ft.commit();
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

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}

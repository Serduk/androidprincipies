package com.sserdiuk.androidprincipies;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements TestFragment.OnFragmentInteractionListener {
    FragmentTransaction ft;
    FragmentManager fragmentManager;
    Fragment fragment;
    Fragment fragment2;

    Fragment testFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new TestFragment();
//        fragment.setText();


//        fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .remove(fragment1)
//        .add(R.id.fragment_container, fragment)
//                .show(fragment2)
//                .hide(fragment3)
//        .commit();
    }

    public void onFragmentClick(View view) {
        ft  = getSupportFragmentManager().beginTransaction();
        testFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        ft.addToBackStack(null);
//        ft.remove(fragment);
        ft.replace(R.id.fragment_placeholder, fragment);
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
        if (fragment != null && fragment.isInLayout()) {

        }
    }
}

package com.sserdiuk.androidprincipies;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * https://habr.com/post/207036/
 * https://developer.android.com/guide/components/fragments?hl=ru
 * https://startandroid.ru/ru/uroki/vse-uroki-spiskom/176-urok-106-android-3-fragments-vzaimodejstvie-s-activity.html
 */
public class ForFragmentsTestingActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction ft;

    TestFragment testFragment;
    TestFragmentTwo testFragmentTwo;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_fragments_testing);

        button = findViewById(R.id.button2);

        testFragment = new TestFragment();
        testFragmentTwo = new TestFragmentTwo();

        /**
         *
         * MAST HAVE FOR FRAGMENTS AND TRANSACTION THEM
         * */
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        fragmentManager.beginTransaction()
//        ft
                .add(R.id.fragment_placeholder, testFragment)
                .addToBackStack("myStack")
                .commit();
    }

    public void onButtonClick(View view) {
//        ft
        fragmentManager.beginTransaction()
                .remove(testFragment)
                .replace(R.id.fragment_placeholder, testFragmentTwo)
                .commit();
    }
}

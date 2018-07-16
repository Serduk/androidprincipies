package com.sserdiuk.androidprincipies;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * https://www.truiton.com/2014/09/android-service-broadcastreceiver-example/
 * */
public class BroadCastReceiverExampleActivity extends AppCompatActivity {
    public static final String mBroadcastStringAction = "com.truiton.broadcast.string";
    public static final String mBroadcastIntegerAction = "com.truiton.broadcast.integer";
    public static final String mBroadcastArrayListAction = "com.truiton.broadcast.arraylist";
    private TextView mTextView;
    private IntentFilter mIntentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver_example);

        mTextView = findViewById(R.id.textview1);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastStringAction);
        mIntentFilter.addAction(mBroadcastIntegerAction);
        mIntentFilter.addAction(mBroadcastArrayListAction);

        Intent serviceIntent = new Intent(this, BroadCastSenderService.class);
        startService(serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mReceiver, mIntentFilter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText(mTextView.getText()
            + "Broadcast from service: \n");
            switch (intent.getAction()) {
                case mBroadcastStringAction:
                    mTextView.setText(mTextView.getText()
                            + intent.getStringExtra("Data") + "\n\n");
                    break;
                case mBroadcastIntegerAction:
                    mTextView.setText(mTextView.getText().toString()
                            + intent.getIntExtra("Data", 0) + "\n\n");
                    break;
                case mBroadcastArrayListAction:
                    mTextView.setText(mTextView.getText()
                            + intent.getStringArrayListExtra("Data").toString()
                            + "\n\n");
                    Intent stopIntent = new Intent(BroadCastReceiverExampleActivity.this,
                            BroadCastSenderService.class);
                    stopService(stopIntent);
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
}

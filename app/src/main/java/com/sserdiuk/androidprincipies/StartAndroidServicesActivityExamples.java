package com.sserdiuk.androidprincipies;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartAndroidServicesActivityExamples extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_examples);

        /** ======================= for lessons 95 ==========================================*/

        tvTask1 = findViewById(R.id.textView);
        tvTask1.setText("Task1");
        tvTask2 = findViewById(R.id.textView2);
        tvTask2.setText("Task 2");
        tvTask3 = findViewById(R.id.textView3);
        tvTask3.setText("Task 3");

        /** ======================= for lessons  ==========================================*/



        /** ======================= for lessons 96 BROADCAST RECEIVER ===============================*/

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(TAG, "onReceive: task = " + task + " , statues = " + status);

                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task 1 start");
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task 2 start");
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task 3 start");
                            break;
                    }
                }

                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            tvTask1.setText("Task 1 Finish, result " + result);
                            break;
                        case TASK2_CODE:
                            tvTask2.setText("Task 2 Finish, result " + result);
                            break;
                        case TASK3_CODE:
                            tvTask3.setText("Task 3 Finish, result " + result);
                            break;
                    }
                }
            }
        };

        IntentFilter filter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, filter);

        /** ======================= for lessons 96 BROADCAST RECEIVER ===============================*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(br);
    }

    public void onStartService(View view) {
        startService(new Intent(this, MyServiceStartAndroidLessons.class));
    }

    public void onStopService(View view) {
        stopService(new Intent(this, MyServiceStartAndroidLessons.class));
    }

/** ======================= for lessons 95  PENDING INTENT ==========================================*/
    final String TAG = StartAndroidServicesActivityExamples.class.getCanonicalName();

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView tvTask1;
    TextView tvTask2;
    TextView tvTask3;

    public void onStart95(View view) {
        PendingIntent pi;
        Intent intent;

//        создаем пендинг интент для таск1
        pi = createPendingResult(TASK1_CODE, null, 0);

//        создаем интент для выова сервиса. куда кладем параметр времени и созданный пендинг интент
        intent = new Intent(this, StartAndroidMyService.class).putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK2_CODE, null, 0);
        intent = new Intent(this, StartAndroidMyService.class).putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK3_CODE, null, 0);
        intent = new Intent(this, StartAndroidMyService.class).putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "RequestCode = " + requestCode + ", resultCode = " + resultCode);
        if (resultCode == STATUS_START) {
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task 1 start");
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task 2 start");
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task 3 start");
                    break;
            }
        }

        if (resultCode == STATUS_FINISH) {
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE:
                    tvTask1.setText("Task 1 Finish, result "+ result);
                    break;
                case TASK2_CODE:
                    tvTask2.setText("Task 2 Finish, result "+ result);
                    break;
                case TASK3_CODE:
                    tvTask3.setText("Task 3 Finish, result "+ result);
                    break;
            }
        }
    }

    /** ======================= for lessons 96 BROADCAST RECEIVER ===============================*/
    BroadcastReceiver br;

    public final static String PARAM_TASK = "task";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.sserdiuk.androidprincipes";
    Button broadcastButton;

    public void onBroadcastClick(View view) {
        Intent intent;

//        создаем интент для вызова сервиса. кладем туда параметр времени икод задачи
        intent = new Intent(this, StartAndroidMyServiceBroadcast.class)
                .putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_TASK, TASK1_CODE);
        startService(intent);

        intent = new Intent(this, StartAndroidMyServiceBroadcast.class)
                .putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_TASK, TASK2_CODE);
        startService(intent);

        intent = new Intent(this, StartAndroidMyServiceBroadcast.class)
                .putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_TASK, TASK3_CODE);
        startService(intent);
    }
}

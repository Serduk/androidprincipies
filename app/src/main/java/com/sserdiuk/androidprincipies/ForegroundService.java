package com.sserdiuk.androidprincipies;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * https://stackoverflow.com/questions/6397754/android-implementing-startforeground-for-a-service
 *
 * */

public class ForegroundService extends Service {
    private static final String TAG = ForegroundService.class.getSimpleName();

    public ForegroundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case Constans.ACTION.STARTFOREGROUND_ACTION:
                Log.i(TAG, "Received Start Foreground Intent ");
                Intent notificationIntent = new Intent(this, MainActivity.class);
                notificationIntent.setAction(Constans.ACTION.MAIN_ACTION);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                        notificationIntent, 0);

                Intent previousIntent = new Intent(this, ForegroundService.class);
                previousIntent.setAction(Constans.ACTION.PREV_ACTION);
                PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                        previousIntent, 0);

                Intent playIntent = new Intent(this, ForegroundService.class);
                playIntent.setAction(Constans.ACTION.PLAY_ACTION);
                PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                        playIntent, 0);

                Intent nextIntent = new Intent(this, ForegroundService.class);
                nextIntent.setAction(Constans.ACTION.NEXT_ACTION);
                PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                        nextIntent, 0);

                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String channelId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId);
                Notification notification = notificationBuilder.setOngoing(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
//                    .setPriority(PRIORITY_MIN)

                        /*
                         * FOR UI PART for clickable buttons and etc
                         * */
//                    .setContentTitle("Truiton Music Player")
//                    .setTicker("Truiton Music Player")
//                    .setContentText("My Music")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setLargeIcon(icon)
//                    .setContentIntent(pendingIntent)
//                    .addAction(android.R.drawable.ic_media_previous,
//                            "Previous", ppreviousIntent)
//                    .addAction(android.R.drawable.ic_media_play, "Play",
//                            pplayIntent)
//                    .addAction(android.R.drawable.ic_media_next, "Next",
//                            pnextIntent)

                        .setCategory(NotificationCompat.CATEGORY_SERVICE)
                        .build();


                startForeground(Constans.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);

                break;
            case Constans.ACTION.PREV_ACTION:
                Log.i(TAG, "Clicked Previous");
                break;
            case Constans.ACTION.PLAY_ACTION:
                Log.i(TAG, "Clicked Play");
                break;
            case Constans.ACTION.NEXT_ACTION:
                Log.i(TAG, "Clicked Next");
                break;
            case Constans.ACTION.STOPFOREGROUND_ACTION:
                Log.i(TAG, "STOP FOREGROUND SERVICE");
                stopForeground(true);
                stopSelf();
                break;
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy in ForeGroundService");
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager) {
        String channelId = "my_service_channelid";
        String channelName = "My Foreground Service";

        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
        // omitted the LED color

        channel.setImportance(NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }
}

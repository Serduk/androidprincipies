package com.sserdiuk.androidprincipies;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static com.sserdiuk.androidprincipies.Constans.NOTIFICATION_ID.CHANNEL_ID;

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
        if (intent.getAction().equals(Constans.ACTION.STARTFOREGROUND_ACTION)) {
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

            Notification notification = new Notification.Builder(this, CHANNEL_ID)
//                    .setContentTitle("Truiton Music Player")
//                    .setTicker("Truiton Music Player")
//                    .setContentText("My Music")
//                    .setSmallIcon(R.drawable.ic_launcher_foreground)
//                    .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
//                    .setLargeIcon(icon)
//                    .setContentIntent(pendingIntent)
//                    .setOngoing(true)
//                    .addAction(android.R.drawable.ic_media_previous, "Previous", ppreviousIntent)
//                    .addAction(android.R.drawable.ic_media_play, "Play", pplayIntent)
//                    .addAction(android.R.drawable.ic_media_next, "Next", pnextIntent)
                    .setContentTitle("TITLE")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setVisibility(Notification.VISIBILITY_PUBLIC)
                    .setChannelId(CHANNEL_ID)
                    .setOnlyAlertOnce(true)
//                    .setPriority(Notification.PRIORITY_MAX)
                    .setWhen(System.currentTimeMillis() + 500)
//                    .setGroup(GROUP)
                    .setOngoing(true)
                    .build();

            startForeground(Constans.NOTIFICATION_ID.FOREGROUND_SERVICE, notification);

        } else if (intent.getAction().equals(Constans.ACTION.PREV_ACTION)) {
            Log.i(TAG, "Clicked Previous");
        } else if (intent.getAction().equals(Constans.ACTION.PLAY_ACTION)) {
            Log.i(TAG, "Clicked Play");
        } else if (intent.getAction().equals(Constans.ACTION.NEXT_ACTION)) {
            Log.i(TAG, "Clicked Next");
        } else if (intent.getAction().equals(Constans.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(TAG, "STOP FOREGROUND SERVICE");
            stopForeground(true);
            stopSelf();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy in ForeGroundService");
    }
}

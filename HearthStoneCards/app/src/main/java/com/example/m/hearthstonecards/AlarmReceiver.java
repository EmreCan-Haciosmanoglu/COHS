package com.example.m.hearthstonecards;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context mContext, Intent intent) {

        System.out.println("calling Alarm receiver2");


        /*Creates an explicit intent for an Activity in your app*/
        Intent resultIntent = new Intent(mContext, Selection.class);
        resultIntent.putExtra("NotificationMessage", "second");
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Intent first = new Intent(mContext, RecyclerActivity.class);
        first.putExtra("NotificationMessage", "second");
        first.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent firstone = PendingIntent.getActivity(mContext, 7, first, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent second = new Intent(mContext, Selection.class);
        second.putExtra("NotificationMessage", "second");
        second.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent secondone = PendingIntent.getActivity(mContext, 8, second, PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Hey Lets Search cards")
                .setContentText("Go Go Go")
                .setAutoCancel(false)
                .setContentIntent(resultPendingIntent)
                .addAction(R.drawable.ic_drink_notification, "Go to Recycler", firstone)
                .addAction(R.drawable.ic_dashboard_black_24dp, "Search", secondone);


        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel("1001", "NOTIFICATION_CHANNEL_NAME2", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId("1001");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(1 /* Request Code */, mBuilder.build());


    }
}

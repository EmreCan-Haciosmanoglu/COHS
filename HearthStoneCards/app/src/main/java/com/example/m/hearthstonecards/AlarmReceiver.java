package com.example.m.hearthstonecards;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    int mNotificationId = 001;
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "AlarmReceiver", Toast.LENGTH_LONG).show();
        // Gets an instance of the NotificationManager service
        final NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(context);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(context.getResources().getText(R.string.notification))
                .setContentText("You have new horoscope")
                .setAutoCancel(true); // clear notification after click

        Intent resultIntent = new Intent(context, Selection.class);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(context, mNotificationId, resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }
}
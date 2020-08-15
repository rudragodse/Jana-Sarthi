package com.example.janasarthi.AarogyaSarthi;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.janasarthi.R;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context,200,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyrudy")
                .setSmallIcon(R.mipmap.doctor)
                .setContentIntent(pendingIntent)
                .setContentTitle("Reminder for precautionary measures")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Have you washed your hands with soap?\nAre you wearing your mask?\nAre you carrying a sanitizer with you?"))
                .setContentText("Have you washed your hands with soap?\nAre you wearing your mask?\nAre you carrying a sanitizer with you?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200,builder.build());
    }
}

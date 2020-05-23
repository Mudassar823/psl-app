package com.sasteyguru.pslscheduleandlivescores;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }
    public void showNotification(String title,String message){

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"Notifications").setContentTitle(title)
                .setSmallIcon(R.drawable.ic_cricket_player).setAutoCancel(true).setContentText(message);
        NotificationManagerCompat compat=NotificationManagerCompat.from(this);
        compat.notify(999,builder.build());

    }
}

//package com.example.letss_talk;
//
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        // Extract message details
//        String title = remoteMessage.getNotification().getTitle();
//        String message = remoteMessage.getNotification().getBody();
//
//        // Show notification
//        showNotification(title, message);
//    }
//
//    private void showNotification(String title, String message) {
//        String channelId = "chat_notifications";
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Create NotificationChannel for Android O and above
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(
//                    channelId,
//                    "Chat Notifications",
//                    NotificationManager.IMPORTANCE_HIGH
//            );
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        // Create Intent to open chat window when notification is clicked
//        Intent intent = new Intent(this, chatwindo);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                intent,
//                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
//        );
//
//        // Build notification
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setSmallIcon(R.drawable.ic_notification) // Replace with your app icon
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent);
//
//        notificationManager.notify(0, notificationBuilder.build());
//    }
//}

package com.wanderboi.module_customnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class MainActivity extends AppCompatActivity {

    Button btnNotify;
    public static final int uniqueID = 123456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotify = (Button) findViewById(R.id.btnNotify);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject json = new JSONObject();
                try {
                    json.put("NotificationMessage", "Hello World");
                    json.put("ID",uniqueID);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent myIntent = new Intent(getApplicationContext(), RecieverActivity.class);
                Bundle extras = new Bundle();
                extras.putString("NotificationMessage",json.toString());
                myIntent.putExtras(extras);

                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, myIntent, 0);


                Notification myNotification = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Exercise of Notification!")
                        .setContentText("Do Something...")
                        .setTicker("Notification!")
                        .addAction(R.drawable.icon_1_white,"Action 1",pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.icon_b_white)
                        .build();

                NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(uniqueID);
                notificationManager.notify(uniqueID, myNotification)
                ;

            }
        });



    }

}

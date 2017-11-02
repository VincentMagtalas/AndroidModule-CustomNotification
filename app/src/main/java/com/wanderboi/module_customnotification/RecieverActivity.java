package com.wanderboi.module_customnotification;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RecieverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);

        TextView txtView = (TextView) findViewById(R.id.txtMessage);


        Bundle extras = getIntent().getExtras();
        String jsonPacket;
        JSONObject json = null;

        if (extras != null) {
            jsonPacket = extras.getString("NotificationMessage");

            try {
                json = new JSONObject(jsonPacket);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // and get whatever type user account id is
            try {
                txtView.setText(json.getString("NotificationMessage"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            try {
                notificationManager.cancel(json.getInt("ID"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            txtView.setText("walang nakuha");
        }
    }
}

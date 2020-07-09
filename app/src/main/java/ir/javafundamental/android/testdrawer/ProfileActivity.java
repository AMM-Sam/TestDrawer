package ir.javafundamental.android.testdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    String CHANNEL_ID = "my_channel_01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText txt_Name = (EditText) findViewById(R.id.txt_Name);
        EditText txt_Family = (EditText) findViewById(R.id.txt_Family);
        EditText txt_Age = (EditText) findViewById(R.id.txt_Age);
        EditText txt_Mail = (EditText) findViewById(R.id.txt_Mail);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        txt_Name.setText(pref.getString("key_Name", ""));
        txt_Family.setText(pref.getString("key_Family", ""));
        txt_Age.setText(pref.getString("key_Age", ""));
        txt_Mail.setText(pref.getString("key_Mail", ""));
        //***************************************************************************
        Button btn_Save = (Button)findViewById(R.id.btn_Save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txt_Name = (EditText) findViewById(R.id.txt_Name);
                EditText txt_Family = (EditText) findViewById(R.id.txt_Family);
                EditText txt_Age = (EditText) findViewById(R.id.txt_Age);
                EditText txt_Mail = (EditText) findViewById(R.id.txt_Mail);

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.putString("key_Name", String.valueOf(txt_Name.getText()));
                editor.putString("key_Family", String.valueOf(txt_Family.getText()));
                editor.putString("key_Age", String.valueOf(txt_Age.getText()));
                editor.putString("key_Mail", String.valueOf(txt_Mail.getText()));

                editor.commit();
                //********************************************************************
                //createNotificationChannel();
                Noti();
                /*
                String idChannel = "my_channel_01";
                Intent mainIntent;

                mainIntent = new Intent(getApplicationContext(), MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, mainIntent, 0);

                NotificationManager mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationChannel mChannel = null;
                // The id of the channel.

                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), null);
                builder.setContentTitle(getApplicationContext().getString(R.string.app_name))
                        //.setSmallIcon(getNotificationIcon())
                        .setContentIntent(pendingIntent)
                        .setContentText(getApplicationContext().getString(R.string.Alarm_Title));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mChannel = new NotificationChannel(idChannel, getApplicationContext().getString(R.string.app_name), importance);
                    // Configure the notification channel.
                    mChannel.setDescription(getApplicationContext().getString(R.string.Alarm_Description));
                    mChannel.enableLights(true);
                    mChannel.setLightColor(Color.RED);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    mNotificationManager.createNotificationChannel(mChannel);
                } else {
                    builder.setContentTitle(getApplicationContext().getString(R.string.app_name))
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent))
                            .setVibrate(new long[]{100, 250})
                            .setLights(Color.YELLOW, 500, 5000)
                            .setAutoCancel(true);
                }
                mNotificationManager.notify(1, builder.build());
                */
            }
        });
    }

    private void Noti(){
        NotificationManager mNotificationManager;

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), "notify_001");
        Intent ii = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Samangi");
        bigText.setBigContentTitle(getString(R.string.Alarm_Title));
        bigText.setSummaryText(getString(R.string.Alarm_Description));

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle(getString(R.string.Alarm_Title));
        mBuilder.setContentText(getString(R.string.Alarm_Description));
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }
    private void createNotificationChannel() {
        try {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.Alarm_Title);
            String description = getString(R.string.Alarm_Description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        else{
            NotificationCompat.Builder builder = new NotificationCompat.Builder(ProfileActivity.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("My notification")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        }
        }
        catch (Exception exc) {
            // Log exception to console
            Log.e("MyApp", "Creating notification channel failed", exc);
        }
    }

}
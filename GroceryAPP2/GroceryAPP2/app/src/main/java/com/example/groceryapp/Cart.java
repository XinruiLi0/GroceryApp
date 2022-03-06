package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cart extends AppCompatActivity {

    Button Call;
    Button Notify;

    int phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Call = (Button) findViewById(R.id.CartCall);
        Notify = (Button) findViewById(R.id.CartNotify);
        phoneNum = 123456;

        Call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNum);
                intent.setData(data);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            //创建通知渠道实例（这三个参数是必须要有的）
            NotificationChannel channel = new NotificationChannel("Notification","Notification",NotificationManager.IMPORTANCE_DEFAULT);
            //创建通知渠道的通知管理器
            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
            //将实例交给管理器
            manager.createNotificationChannel(channel);
        }


       Notify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Cart.this,"Notification");
                builder.setContentText("You have items in cart!");
                builder.setContentTitle("Grocery Notification!");
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Cart.this);
                managerCompat.notify(1,builder.build());
            }
        });
    }



}
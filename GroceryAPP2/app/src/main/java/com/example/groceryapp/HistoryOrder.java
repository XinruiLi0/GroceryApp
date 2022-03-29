package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryOrder extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int userID;
    BottomNavigationView bottomNavigationView;
    ImageButton Call;
    Button Notify;

    int phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Extract user id from local
        // TODO

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select * from Orders where CustomerId = "+userID);

        // Show the order detail in view
        // TODO

        Call = (ImageButton) findViewById(R.id.cartCall);
        Notify = (Button) findViewById(R.id.cartNotify);
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
            NotificationChannel channel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            //创建通知渠道的通知管理器
            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
            //将实例交给管理器
            manager.createNotificationChannel(channel);
        }


        Notify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(HistoryOrder.this,"Notification");
                builder.setContentText("You have items in cart!");
                builder.setContentTitle("Grocery Notification!");
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(HistoryOrder.this);
                managerCompat.notify(1,builder.build());
            }
        });
    }


    // navigation view
//    CustomerAccount customerAccount = new CustomerAccount();
//    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // jump to customer home page
            case R.id.home:
                Intent homeIntent = new Intent(HistoryOrder.this, GroceryStores.class);
                startActivity(homeIntent);
                return true;

            case R.id.account:
//                Intent accountIntent = new Intent(GroceryStores.this, CustomerAccount.class);
//                startActivity(accountIntent);
                return true;
        }

        return false;
    }


}
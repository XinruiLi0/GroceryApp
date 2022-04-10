package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.TextView;

import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.example.groceryapp.ItemHelper.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryOrder extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private int userID;
    BottomNavigationView bottomNavigationView;
    ImageButton Call;
    Button Notify;
    Button Logout;
    private String storeID;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


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
        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
       //需要在cart 加入intent.putExtra("userID",userID)
        // userID = intent.getIntExtra("userID");

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select * from Orders where CustomerId = "+userID);

        // Show the order detail in view
        // TODO
        recyclerView = findViewById(R.id.itemRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ItemHelperClass> locations = new ArrayList<>();

        for (int i = 0; i < orderList.size(); ++i) {
            locations.add(new ItemHelperClass(
                    orderList.get(i).get(1),
                    orderList.get(i).get(0),
                    orderList.get(i).get(2),
                    orderList.get(i).get(3),
                    orderList.get(i).get(4),
                    orderList.get(i).get(5),
                    orderList.get(i).get(6)));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        Call = (ImageButton) findViewById(R.id.cartCall);
        Notify = (Button) findViewById(R.id.cartNotify);
        Logout = (Button) findViewById(R.id.Logout);
        //phoneNum = 123456;
        //需要从数据库中读取手机号
        ArrayList<ArrayList<String>> phonelist = DBUtil.Query("select * from Retailers where storeId = "+storeID);
        phoneNum =  Integer.parseInt(phonelist.get(0).get(0));

        Call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNum);
                intent.setData(data);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);

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

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryOrder.this,MainActivity.class);
                startActivity(intent);
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
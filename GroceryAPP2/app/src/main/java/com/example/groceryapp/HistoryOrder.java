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

import com.example.groceryapp.CartHelper.CartHelperClass;
import com.example.groceryapp.CartHelper.MyAdapter;
import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HistoryOrder extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private String userID;
    private String orderNumber;
    private String name;
    private String phoneNumber;
    BottomNavigationView bottomNavigationView;
    ImageButton Call;
    Button Notify;

    private ImageButton back;
    private double price;

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
        bottomNavigationView.setSelectedItemId(R.id.account);


        // Extract user id from local
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
        orderNumber = intent.getStringExtra("orderNumber");
        name = intent.getStringExtra("name");
        phoneNumber = intent.getStringExtra("phoneNumber");

        // Request order list from db
        String temp = "select ItemName, ItemImage, Quantities, Price, TotalPrice from Orders join Products on Orders.ItemId = Products.id where OrderNumber = "+orderNumber;
        ArrayList<ArrayList<String>> orderDetail = DBUtil.Query("select ItemName, ItemImage, Quantities, Price, TotalPrice from Orders join Products on Orders.ItemId = Products.id where OrderNumber = "+orderNumber);

        // Show the order detail in view
        recyclerView = findViewById(R.id.orderDetailRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CartHelperClass> locations = new ArrayList<>();

        price = 0;
        for (int i = 0; i < orderDetail.size(); ++i) {
            price = Double.parseDouble(orderDetail.get(i).get(4));
            locations.add(new CartHelperClass(
                    orderDetail.get(i).get(0),
                    "",
                    "",
                    "",
                    orderDetail.get(i).get(3),
                    "",
                    orderDetail.get(i).get(1),
                    orderDetail.get(i).get(2)));
        }
        price = (double) Math.round(price * 100) / 100;

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText("Total: $" + price);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(HistoryOrder.this, CustomerAccount.class);
                intent.putExtra("id", userID);
                startActivity(intent);
            }
        });

        Call = (ImageButton) findViewById(R.id.cartCall);
        Notify = (Button) findViewById(R.id.cartNotify);

        Call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNumber);
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

    }

    // navigation view
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // jump to home page
            case R.id.home:
                Intent intent = new Intent(HistoryOrder.this, GroceryStores.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                return true;

            case R.id.account:
                return true;
        }

        return false;
    }

}


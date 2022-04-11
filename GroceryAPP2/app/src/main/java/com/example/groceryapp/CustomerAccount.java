package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.groceryapp.OrderHelper.OrderHelperClass;
import com.example.groceryapp.OrderHelper.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerAccount extends AppCompatActivity {
    ImageButton back;

    private String userID;
    private String userName;
    private String storeID;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);


        // Extract user id from local
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        // userID = intent.getIntExtra("userID");

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select OrderNumber, PurchaseTime, PhoneNumber, StoreName "
                + "from Orders join Retailers on Orders.RetailerId = Retailers.id where CustomerId = "
                + userID + " group by OrderNumber, PurchaseTime, PhoneNumber, StoreName");

        // Show the order detail in view
        recyclerView = findViewById(R.id.orderRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<OrderHelperClass> locations = new ArrayList<>();
        for (int i = 0; i < orderList.size(); ++i) {
            locations.add(new OrderHelperClass(
                    userID,
                    orderList.get(i).get(0),
                    orderList.get(i).get(1),
                    orderList.get(i).get(3),
                    orderList.get(i).get(2)));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

    }
}

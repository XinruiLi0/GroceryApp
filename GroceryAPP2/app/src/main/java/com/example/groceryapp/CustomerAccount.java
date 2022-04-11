package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.groceryapp.OrderHelper.OrderHelperClass;
import com.example.groceryapp.OrderHelper.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CustomerAccount extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private String userID;
    private String userName;
    private String storeID;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.account);

        // Extract user id from local
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        if (userID == null) {
            userID = intent.getStringExtra("id");
        }

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select OrderNumber, PurchaseTime, PhoneNumber, StoreName "
                + "from Orders join Retailers on Orders.RetailerId = Retailers.id where CustomerId = "
                + userID + " group by OrderNumber, PurchaseTime, PhoneNumber, StoreName order by OrderNumber desc");

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

    // navigation view
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // jump to home page
            case R.id.home:
                Intent intent = new Intent(CustomerAccount.this, GroceryStores.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                return true;

            case R.id.account:
                return true;
        }

        return false;
    }
}

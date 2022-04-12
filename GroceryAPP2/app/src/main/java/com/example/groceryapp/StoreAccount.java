package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.groceryapp.OrderHelper.MyAdapter;
import com.example.groceryapp.OrderHelper.OrderHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StoreAccount extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ImageButton next;
    private String userID;
    private String userName;
    private String storeID;
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_account);

        bottomNavigationView = findViewById(R.id.bottomNavigationView3);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.account);

//        next = (ImageButton) findViewById(R.id.imageButton4);
//        next.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // forward
//                Intent intent = new Intent(StoreAccount.this, viewOrder.class);
//                startActivity(intent);
//            }
//        });

        // Extract user id from local
        Intent intent = getIntent();
        storeID = intent.getStringExtra("userID");
        if (storeID == null) {
            storeID = intent.getStringExtra("id");
        }

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select OrderNumber, PurchaseTime, PhoneNumber, StoreName "
                + "from Orders join Retailers on Orders.RetailerId = Retailers.id where RetailerId = 3 group by OrderNumber, PurchaseTime, PhoneNumber, StoreName order by OrderNumber desc");

        // Show the order detail in view
        recyclerView = findViewById(R.id.StoreOrderRecyclerView);

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
            case R.id.home:
                Intent homeIntent = new Intent( StoreAccount.this, StoreHome.class);
                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
                return true;
        }

        return false;
    }

}
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

import com.example.groceryapp.CartHelper.CartHelperClass;
import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.example.groceryapp.ItemHelper.MyAdapter;
import com.example.groceryapp.StoreHelper.StoreHelperClass;
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
        // TODO
        recyclerView = findViewById(R.id.itemRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ItemHelperClass> locations = new ArrayList<>();
        for (int i = 0; i < orderList.size(); ++i) {

        }


        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        back = (ImageButton) findViewById(R.id.accountBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Logout = (Button) findViewById(R.id.customerlogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerAccount.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

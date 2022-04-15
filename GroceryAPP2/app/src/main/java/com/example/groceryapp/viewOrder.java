package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.groceryapp.CartHelper.CartHelperClass;
import com.example.groceryapp.CartHelper.MyAdapter;

import java.util.ArrayList;

public class viewOrder extends AppCompatActivity {

    private int storeID;
    ImageButton back;
    private String userID;
    private String orderNumber;
    private String name;
    private String phoneNumber;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        // Extract store id from local
        Intent intent = getIntent();
        userID = intent.getStringExtra("id");
        orderNumber = intent.getStringExtra("orderNumber");

        // Request order list from db
//        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select * from Orders where RetailerId = "+storeID);
        String temp = "select ItemName, ItemImage, Quantities, Price, TotalPrice from Orders join Products on Orders.ItemId = Products.id where OrderNumber = "+orderNumber;
        ArrayList<ArrayList<String>> orderDetail = DBUtil.Query("select ItemName, ItemImage, Quantities, Price, TotalPrice from Orders join Products on Orders.ItemId = Products.id where OrderNumber = "+orderNumber);

        // Show the order detail in view
        recyclerView = findViewById(R.id.storeODetailRecyclerView);

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
        TextView totalPrice = (TextView) findViewById(R.id.textTotalPrice);
        totalPrice.setText("Total Price: $" + price);

        back = (ImageButton) findViewById(R.id.imageButton6);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(viewOrder.this, StoreAccount.class);
                startActivity(intent);
            }
        });
    }
}
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
import android.widget.EditText;

import com.example.groceryapp.StoreProductHelper.MyAdapter;
import com.example.groceryapp.StoreProductHelper.StoreProductHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class StoreHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private String storeID;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Extract store id from local
        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
        System.out.println(storeID);

        // Request product list from db
        ArrayList<ArrayList<String>> productList = DBUtil.Query("select * from Products where RetailerId = " + storeID);

        // Show the products in view
        // TODO
        recyclerView = findViewById(R.id.storeProductRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<StoreProductHelperClass> locations = new ArrayList<>();

        for (int i = 0; i < productList.size(); ++i) {
            locations.add(new StoreProductHelperClass(
                    productList.get(i).get(1),
                    productList.get(i).get(0),
                    productList.get(i).get(2),
                    productList.get(i).get(3),
                    productList.get(i).get(4),
                    productList.get(i).get(5)));
//                    productList.get(i).get(6)));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        // jump to add product fragment
        Button addproduct = (Button) findViewById(R.id.addProductButton);
        addproduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addProductIntent = new Intent(StoreHome.this, AddProduct.class);
                startActivity(addProductIntent);
            }
        });
    }

    // navigation view
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
//                Intent homeIntent = new Intent( .this, StoreHome.class);
//                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
                Intent accountIntent = new Intent(StoreHome.this, StoreAccount.class);
                startActivity(accountIntent);
                return true;
        }

        return false;
    }
}
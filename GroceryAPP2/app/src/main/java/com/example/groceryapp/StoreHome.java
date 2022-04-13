package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.groceryapp.StoreProductHelper.MyAdapter;
import com.example.groceryapp.StoreProductHelper.StoreProductHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class StoreHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private String sharedStoreId;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Extract store id from local
        sharedPreferences = getSharedPreferences("StorePrefs", Context.MODE_PRIVATE);
        sharedStoreId = sharedPreferences.getString("storeId", null);
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Request product list from db
        ArrayList<ArrayList<String>> productList = DBUtil.Query("select * from Products where RetailerId = " + sharedStoreId);
        // Show the products in view
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
                    productList.get(i).get(5),
                    productList.get(i).get(7)));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        // jump to add product fragment
        Button addProduct = (Button) findViewById(R.id.addProductButton);
        addProduct.setOnClickListener(new View.OnClickListener() {
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
package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GroceryStores extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    Button Share1;
    Button Map1;

    Button store1;
    Button Share2;

    private String userID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        Share1 = (Button) findViewById(R.id.storeShare1);
        Map1 = (Button) findViewById(R.id.StoreLocation1);
        store1 = (Button) findViewById(R.id.Store1);
        Share2 = (Button) findViewById(R.id.storeShare2);

        // Extract user name from local
        Intent i = getIntent();
        userID = i.getStringExtra("userID");
        userName = i.getStringExtra("userName");

        // Request store list from db
        ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers");

        // Show the stores in view
        // TODO

        Share1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Share this store");
                intent.setType("text/plain");
                startActivity(intent);

            }
        });

        Map1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,Map.class);
                startActivity(intent);

            }
        });
        store1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,shopCategory.class);

                startActivity(intent);

            }
        });

        Share2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,Cart.class);
                startActivity(intent);

            }
        });
    }

    // navigation view
//    CustomerAccount customerAccount = new CustomerAccount();
//    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
//                Intent homeIntent = new Intent(GroceryStores.this, GroceryStores.class);
//                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
                Intent accountIntent = new Intent(GroceryStores.this, CustomerAccount.class);
                startActivity(accountIntent);
                return true;

        }

        return false;
    }


}
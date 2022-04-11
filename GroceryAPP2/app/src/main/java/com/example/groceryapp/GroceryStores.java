package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.groceryapp.StoreHelper.MyAdapter;
import com.example.groceryapp.StoreHelper.StoreHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GroceryStores extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    private String userID;
    private String userName;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Extract user name from local
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        userName = intent.getStringExtra("userName");

        // Request store list from db
        ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers");

        // Show the stores in view
        recyclerView = findViewById(R.id.storeRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<StoreHelperClass> locations = new ArrayList<>();

        for (int i = 0; i < result.size(); ++i) {
            locations.add(new StoreHelperClass(result.get(i).get(1), result.get(i).get(0), userName, userID));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

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
                Intent intent = new Intent(GroceryStores.this, CustomerAccount.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
                return true;
        }

        return false;
    }


}
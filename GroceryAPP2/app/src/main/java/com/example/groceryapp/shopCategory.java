package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.groceryapp.ItemHelper.MyAdapter;
import com.example.groceryapp.ItemHelper.ItemHelperClass;

import java.util.ArrayList;

public class shopCategory extends AppCompatActivity {

    private String storeID;
    private String userName;
    ImageButton back;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);

        // Extract store id and user name from local
        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
        userName = intent.getStringExtra("userName");

        // Request product list from db
        ArrayList<ArrayList<String>> itemList = DBUtil.Query("select * from Products where RetailerId = " + storeID);
        // Optional: Request categories list from db
        // ArrayList<ArrayList<String>> categoryList = DBUtil.Query("select ItemCategory from Products where RetailerId = " + storeID + " group by ItemCategory");

        // Show the items in view
        recyclerView = findViewById(R.id.itemRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<ItemHelperClass> locations = new ArrayList<>();

        for (int i = 0; i < itemList.size(); ++i) {
            locations.add(new ItemHelperClass(
                    itemList.get(i).get(1),
                    itemList.get(i).get(0),
                    itemList.get(i).get(2),
                    itemList.get(i).get(3),
                    itemList.get(i).get(4),
                    itemList.get(i).get(5),
                    itemList.get(i).get(6)));
        }

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        ImageButton cart = (ImageButton) findViewById(R.id.imageButton);
        back = (ImageButton) findViewById(R.id.imageButton2);

        cart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(shopCategory.this, Cart.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(shopCategory.this, GroceryStores.class);
                startActivity(intent);
            }
        });
    }
}
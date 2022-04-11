package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.groceryapp.ItemHelper.MyAdapter;
import com.example.groceryapp.ItemHelper.ItemHelperClass;

import java.util.ArrayList;

public class shopCategory extends AppCompatActivity {

    private String storeID;
    private String userID;
    private ArrayList<ArrayList<String>> itemList;
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
        userID = intent.getStringExtra("userID");

        // Request product list from db
        itemList = DBUtil.Query("select * from Products where RetailerId = " + storeID);
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
                ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    EditText amount = (EditText) ((CardView) recyclerView.getChildAt(i)).findViewById(R.id.number);
                    if (!amount.getText().toString().isEmpty() && Integer.parseInt(amount.getText().toString()) > 0) {
                        if (itemList.get(i).size() == 8) {
                            itemList.get(i).add(amount.getText().toString());
                        } else {
                            itemList.get(i).set(8, amount.getText().toString());
                        }
                        items.add(itemList.get(i));
                    }
                }
                Intent intent = new Intent(shopCategory.this, Cart.class);
                intent.putExtra("storeID", storeID);
                intent.putExtra("userID", userID);
                intent.putExtra("itemList", items);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(shopCategory.this, GroceryStores.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }
}
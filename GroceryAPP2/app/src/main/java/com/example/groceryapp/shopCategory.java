package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class shopCategory extends AppCompatActivity {

    private int storeID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);

        EditText cucumber = (EditText) findViewById(R.id.cucumbernumber);
        EditText blueberry = (EditText) findViewById(R.id.blueberrynumber);
        EditText eggs = (EditText) findViewById(R.id.eggnumber);
        EditText garlic = (EditText) findViewById(R.id.garlicnumber);

        ImageButton cart = (ImageButton) findViewById(R.id.imageButton);

        // Extract store id and user name from local
        // TODO

        // Request product list from db
        ArrayList<ArrayList<String>> productList = DBUtil.Query("select * from Products where RetailerId = " + storeID);
        // Optional: Request categories list from db
        // ArrayList<ArrayList<String>> categoryList = DBUtil.Query("select ItemCategory from Products where RetailerId = " + storeID + " group by ItemCategory");

        // Show the products in view
        // TODO

        cart .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(shopCategory.this, Cart.class);
                startActivity(intent);

            }
        });
    }
}
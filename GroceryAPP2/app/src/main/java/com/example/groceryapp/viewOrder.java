package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class viewOrder extends AppCompatActivity {

    private int storeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        // Extract store id from local
        // TODO

        // Request order list from db
        ArrayList<ArrayList<String>> orderList = DBUtil.Query("select * from Orders where RetailerId = "+storeID);

        // Show the order detail in view
        // TODO
    }
}
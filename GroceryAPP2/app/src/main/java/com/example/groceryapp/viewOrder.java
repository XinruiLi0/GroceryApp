package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class viewOrder extends AppCompatActivity {

    private int storeID;
    ImageButton back;

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
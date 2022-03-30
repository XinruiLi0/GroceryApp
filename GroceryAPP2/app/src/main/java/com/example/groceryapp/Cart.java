package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cart extends AppCompatActivity {

    private int storeID;
    private int userID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView cucumber = (TextView) findViewById(R.id.cucumbercart);
        TextView bluebery = (TextView) findViewById(R.id.blueberrycart);
        TextView eggs = (TextView) findViewById(R.id.eggcart);
        TextView garlic = (TextView) findViewById(R.id.garliccart);

        Button checkout = (Button) findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Extract store id and user id from local
                // TODO

                // Transfer item to a list
                // TODO

                // Get order date and time
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String currentTime = formatter.format(date);

                // Submit the order
                // TODO for each item (will be better to execute sql in parallel)
                // int temp = DBUtil.Update("insert into Orders(PurchaseTime, RetailerId, CustomerId, ItemId, Amount) values ('"+currentTime+"', "+storeID+", "+userID+", "+itemID+", "+Amount+")");
                // Adjust item stock in database
                // int temp = DBUtil.Update("update Products set ItemStock = ItemStock - "+Amount+" where id = "+itemID);

                // To selection menu
                Intent intent = new Intent(Cart.this, HistoryOrder.class);
                startActivity(intent);

            }
        });

    }



}
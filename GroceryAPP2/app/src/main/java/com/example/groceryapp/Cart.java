package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cart extends AppCompatActivity {


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
                // To selection menu
                Intent intent = new Intent(Cart.this,HistoryOrder.class);
                startActivity(intent);

            }
        });

    }



}
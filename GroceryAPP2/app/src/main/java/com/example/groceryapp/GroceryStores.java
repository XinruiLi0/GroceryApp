package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GroceryStores extends AppCompatActivity {

    Button Share1;
    Button Map1;

    Button store1;
    Button Share2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);

        Share1 = (Button) findViewById(R.id.storeShare1);
        Map1 = (Button) findViewById(R.id.StoreLocation1);
        store1 = (Button) findViewById(R.id.Store1);
        Share2 = (Button) findViewById(R.id.storeShare2);

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
}
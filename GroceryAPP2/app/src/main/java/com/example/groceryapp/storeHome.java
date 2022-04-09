package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class storeHome extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ImageButton next;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView3);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.account);

        next = (ImageButton) findViewById(R.id.imageButton4);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // forward
                Intent intent = new Intent(storeHome.this, viewOrder.class);
                startActivity(intent);
            }
        });
    }
// navigation view
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent homeIntent = new Intent( storeHome.this, addproduct.class);
                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
//                Intent accountIntent = new Intent(addproduct.this, storeHome.class);
//                startActivity(accountIntent);
                return true;
        }

        return false;
    }

}
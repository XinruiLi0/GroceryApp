package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class addproduct extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    private int storeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Extract store id from local
        // TODO

        // Request product list from db
        ArrayList<ArrayList<String>> productList = DBUtil.Query("select * from Products where RetailerId = " + storeID);

        // Show the products in view
        // TODO

        EditText cucumber = (EditText) findViewById(R.id.cucumberadd);
        EditText blueberry = (EditText) findViewById(R.id.blueberryadd);
        EditText eggs = (EditText) findViewById(R.id.eggadd);
        EditText garlic = (EditText) findViewById(R.id.garlicadd);

        Button addproduct = (Button) findViewById(R.id.addproduct);


        addproduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
    }

    // navigation view
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
//                Intent homeIntent = new Intent( .this, addproduct.class);
//                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
                Intent accountIntent = new Intent(addproduct.this, storeHome.class);
                startActivity(accountIntent);
                return true;
        }

        return false;
    }
}
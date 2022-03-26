package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class addproduct extends AppCompatActivity {

    private int storeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

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
        Button vieworder = (Button) findViewById(R.id.vieworder);

        addproduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(addproduct.this,viewOrder.class);
                startActivity(intent);

            }
        });
    }
}
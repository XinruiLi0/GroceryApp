package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addproduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);


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
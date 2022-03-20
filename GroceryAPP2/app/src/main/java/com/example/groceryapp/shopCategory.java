package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class shopCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category);

        EditText cucumber = (EditText) findViewById(R.id.cucumbernumber);
        EditText blueberry = (EditText) findViewById(R.id.blueberrynumber);
        EditText eggs = (EditText) findViewById(R.id.eggnumber);
        EditText garlic = (EditText) findViewById(R.id.garlicnumber);

        ImageButton cart = (ImageButton) findViewById(R.id.imageButton);
        cart .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(shopCategory.this,Cart.class);
                startActivity(intent);

            }
        });
    }
}
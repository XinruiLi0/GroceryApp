package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Customer = (Button) findViewById(R.id.Customer);
        Customer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });
    }
}
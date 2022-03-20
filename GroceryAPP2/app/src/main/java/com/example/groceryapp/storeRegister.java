package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class storeRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_register);

        EditText storename = (EditText) findViewById(R.id.storenameregister);
        EditText storeaddress = (EditText) findViewById(R.id.storeaddressregister);
        EditText email = (EditText) findViewById(R.id.emailregister);
        EditText password = (EditText) findViewById(R.id.passwordregister);
        EditText phone = (EditText) findViewById(R.id.phoneregister);

        Button Rgister = (Button) findViewById(R.id.registerstore);

        Button Signin = (Button) findViewById(R.id.registerstore2);
        Signin.setBackgroundColor(Color.TRANSPARENT);

        Rgister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(storeRegister.this,addproduct.class);
                startActivity(intent);

            }
        });

        Signin .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(storeRegister.this,storeSignin.class);
                startActivity(intent);

            }
        });
    }
}
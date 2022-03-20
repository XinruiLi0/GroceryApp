package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class storeSignin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_signin);

        EditText email = (EditText) findViewById(R.id.emailsignin);
        EditText password = (EditText) findViewById(R.id.passwordsignin);
        Button Signin = (Button) findViewById(R.id.signinstore);

        Signin .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(storeSignin.this,addproduct.class);
                startActivity(intent);

            }
        });
    }
}
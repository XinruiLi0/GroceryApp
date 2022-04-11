package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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
                // Check retailer information
                ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers where Email = '"+email.getText()+"' and Password = '"+password.getText()+"'");
                if (result.isEmpty()) {
                    Toast.makeText(storeSignin.this,"Incorrect email or password!", Toast.LENGTH_LONG).show();
                } else {
                    // create the Shared Preferences to share the store id to local
                    SharedPreferences sharedPreferences = getSharedPreferences("StorePrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =  sharedPreferences.edit();
                    editor.putString("storeId", result.get(0).get(0));
                    editor.commit();

                    // Jump to next page with store id and store name
                    Intent intent = new Intent(storeSignin.this, StoreHome.class);
                    startActivity(intent);
                }
            }
        });
    }
}
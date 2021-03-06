package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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
        EditText longitude = (EditText) findViewById(R.id.longitude);
        EditText latitude = (EditText) findViewById(R.id.latitude);

        Button Rgister = (Button) findViewById(R.id.registerstore);

        Button Signin = (Button) findViewById(R.id.registerstore2);
        Signin.setBackgroundColor(Color.TRANSPARENT);

        Rgister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check the syntax of the inputs
                // TODO

                // Update user info to db
                int temp = DBUtil.Update("insert into Retailers(StoreName, PhoneNumber, StoreAddress, longitude, latitude, Email, [Password]) values ('"+storename.getText()+"', "+phone.getText()+", '"+storeaddress.getText()+"', "+longitude.getText()+", "+latitude.getText()+", '"+email.getText()+"', '"+password.getText()+"')");
                if (temp == 1) {
                    // Success
                    Toast.makeText(storeRegister.this,"Success!", Toast.LENGTH_LONG).show();
                    ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers where Email = '"+email.getText()+"' and Password = '"+password.getText()+"'");

                    SharedPreferences sharedPreferences = getSharedPreferences("StorePrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =  sharedPreferences.edit();
                    editor.putString("storeId", result.get(0).get(0));
                    editor.commit();

                    // Jump to next page with store id and store name
                    Intent intent = new Intent(storeRegister.this, StoreHome.class);
                    startActivity(intent);
                } else {
                    // Error
                    Toast.makeText(storeRegister.this,"Error!", Toast.LENGTH_LONG).show();
                }
            }
        });

        Signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(storeRegister.this,storeSignin.class);
                startActivity(intent);
            }
        });
    }
}
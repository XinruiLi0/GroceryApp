package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


                ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers where Email = '"+email.getText()+"' and Password = '"+password.getText()+"'");
                if (result.isEmpty()) {
                    Toast.makeText(storeSignin.this,"Incorrect email or password!", Toast.LENGTH_LONG).show();
                } else {
                    // Store store id and store name in local
                    int storeID = Integer.parseInt(result.get(0).get(0));
                    // TODO

                    // To selection menu
                    Intent intent = new Intent(storeSignin.this,addproduct.class);
                    startActivity(intent);
                }


            }
        });
    }
}
package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        EditText email = (EditText) findViewById(R.id.signInEmail);
        EditText password = (EditText) findViewById(R.id.signInPassword);
        Button signin = (Button) findViewById(R.id.signin);
        Button register = (Button) findViewById(R.id.signinRegister);

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Check retailer information
                ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Customers where Email = '"+email.getText()+"' and Password = '"+password.getText()+"'");
                if (result.isEmpty()) {
                    Toast.makeText(Signin.this,"Incorrect email or password!", Toast.LENGTH_LONG).show();
                } else {
                    // Jump to next page with store id and store name
                    Intent intent = new Intent(Signin.this, GroceryStores.class);
//                    intent.putExtra("userID",Integer.parseInt(result.get(0).get(0)));
//                    intent.putExtra("userName",result.get(0).get(1));
//                    startActivity(intent);
                    startActivity(intent);
                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Jump to register page
                Intent intent = new Intent(Signin.this, Register.class);
//                    intent.putExtra("userID",Integer.parseInt(result.get(0).get(0)));
//                    intent.putExtra("userName",result.get(0).get(1));
//                    startActivity(intent);
                startActivity(intent);
            }
        });
    }
}
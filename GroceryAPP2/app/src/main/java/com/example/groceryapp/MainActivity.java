package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button Customer;
    Button Retailer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Customer = (Button) findViewById(R.id.Customer);
        Retailer = (Button) findViewById(R.id.Grocery);

        Customer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });

        Retailer .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(MainActivity.this,storeRegister.class);
                startActivity(intent);

            }
        });


//        // Will be removed in final prototype
//        Button test = (Button) findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                int temp = DBUtil.Update("insert into test (string, integer, boolean) values ('Test string', 123, 0)");
//                ArrayList<ArrayList<String>> result = DBUtil.Query("select * from test");
//                test.setText(result.toString());
//            }
//        });
    }
}
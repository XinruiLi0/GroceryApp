package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

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

        Button test = (Button) findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBUtil db = new DBUtil();
                Connection conn = db.conclass();
                if (conn != null) {
                    try {
                        ArrayList<ArrayList<String>> result = new ArrayList<>();
                        String sql = "select * from test";
                        Statement smt = conn.createStatement();
                        ResultSet rs = smt.executeQuery(sql);
                        ResultSetMetaData rsm =rs.getMetaData();
                        int col = rsm.getColumnCount();
                        while (rs.next()) {
                            ArrayList<String> row = new ArrayList<String>();
                            for (int i = 1; i <= col; i++) {
                                row.add(rs.getString(i));
                            }
                            result.add(row);
                        }
                        test.setText(result.toString());
                        conn.close();
                    } catch (Exception e) {
                        Log.e("Error :", e.getMessage());
                    }
                }

            }
        });
    }
}
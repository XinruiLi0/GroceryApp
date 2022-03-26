package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register extends AppCompatActivity {
   // public static String url = "jdbc:mysql://localhost:3306/grocery";
    public static String url = "jdbc:mysql://grocery.cb65oow2kgxz.us-east-1.rds.amazonaws.com:3306/grocery";
    public static String user = "admin";
    public static String pass = "123123123";

    Button Register;
    Button Sign_in;


    EditText Name;
    EditText Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = (EditText) findViewById(R.id.registerName);
        Password = (EditText) findViewById(R.id.registerPassword);
        // Email

        Register  = (Button) findViewById(R.id.registerRegis);
        Sign_in  = (Button) findViewById(R.id.registerSignin);


        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Update user info to db
                int temp = DBUtil.Update("insert into Customers(UserName, Email, [Password]) values ('"+Name+"', '"+Name+"', '"+Password+"')");
                if (temp == 1) {
                    // Success
                    Toast.makeText(Register.this,"Success!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this,GroceryStores.class);
                    startActivity(intent);
                } else {
                    // Error
                    Toast.makeText(Register.this,"Error!", Toast.LENGTH_LONG).show();
                }
            }
        });
        /*
        Sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu

                String pass = readPass(Name.getText().toString());
                if(pass.equals(Password.getText().toString())){
                    Intent intent = new Intent(Register.this,GroceryStores.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Register.this,"Wrong Password!", Toast.LENGTH_LONG).show();
                }
            }
        }); */

    }

    public static void add(String Name, String Password) {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, pass);

                Statement statement = connection.createStatement();
                statement.execute("INSERT INTO CustomerTable (Name, Password) VALUES ('w','w')");
                //statement.execute("INSERT INTO CustomerTable (Name, Password) VALUES ('" + Name + "','" + Password + "')");
                connection.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String readPass(String Name) {
        String password = null;
        try {
            //Class.forName("org.mysql.Driver");
           Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT Password from CustomerTable WHERE NAME = ('w");
            if (resultSet.next()) {
               password = resultSet.getString("Password");
            }
            connection.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return password;
    }

}
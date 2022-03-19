package com.example.groceryapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static String IP = "database-1.clc4hmfd10vk.us-east-1.rds.amazonaws.com";
    private static String DBName = "grocery";
    private static String USER = "admin";
    private static String PWD = "groceryapp";

    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + IP + ":1433/" + DBName, USER, PWD);
        } catch (Exception e) {
            Log.e("Error :", e.getMessage());
        }
        return con;
    }
}

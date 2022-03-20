package com.example.groceryapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DBUtil {
    public static ArrayList<ArrayList<String>> Query(String sql) {
        SQLConnection db = new SQLConnection();
        Connection conn = db.conclass();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (conn != null) {
            try {
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
                conn.close();
            } catch (Exception e) {
                Log.e("Error :", e.getMessage());
            }
        }
        return result;
    }
}

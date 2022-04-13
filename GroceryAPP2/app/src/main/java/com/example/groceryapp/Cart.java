package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.groceryapp.CartHelper.CartHelperClass;
import com.example.groceryapp.CartHelper.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cart extends AppCompatActivity {

    private String storeID;
    private String userID;
    private String userName;
    private ArrayList<ArrayList<String>> itemList;
    ImageButton back;
    private double price;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Extract store id and user id from local
        // TODO
        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
        userID = intent.getStringExtra("userID");
        ArrayList<ArrayList<String>> selectedList = (ArrayList<ArrayList<String>>) intent.getSerializableExtra("itemList");

        // Request product list from db
        itemList = DBUtil.Query("select id, ItemName, ItemStock, RestockTime, ItemPrice, ItemCategory, ItemImage, RetailerId from Products where RetailerId = " + storeID);

        for (ArrayList<String> list : itemList) {
            for (ArrayList<String> selected : selectedList) {
                if (list.get(0).equals(selected.get(0))) {
                    list.add(selected.get(1));
                }
            }
        }

        // Show the items in view
        recyclerView = findViewById(R.id.CardRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CartHelperClass> locations = new ArrayList<>();

        price = 0;
        for (ArrayList<String> list : itemList) {
            if (list.size() == 9) {
                locations.add(new CartHelperClass(
                        list.get(1),
                        list.get(0),
                        list.get(2),
                        list.get(3),
                        list.get(4),
                        list.get(5),
                        list.get(6),
                        list.get(8)));
                price += Double.parseDouble(list.get(4)) * Integer.parseInt(list.get(8));
            }

        }
        price = (double) Math.round(price * 100) / 100;

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);
        totalPrice.setText("Total: $" + price);

        Button checkout = (Button) findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get order date and time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                String currentTime = formatter.format(date);
                String orderNumber = currentTime.replaceAll("[[\\s-:punct:]]", "");

                for (ArrayList<String> list : itemList) {
                    if (list.size() == 9) {
                        // Submit the order
                        int temp = DBUtil.Update("insert into Orders(OrderNumber, PurchaseTime, RetailerId, CustomerId, ItemId, Quantities, Price, TotalPrice) \n" +
                                "values ('"+orderNumber+"', '"+currentTime+"', "+storeID+", "+userID+", "+list.get(0)+", "+list.get(8)+", "+list.get(4)+", "+price+")");
                        if (temp == 1) {
                            // Adjust item stock in database
                            DBUtil.Update("update Products set ItemStock = ItemStock - "+list.get(8)+" where id = "+list.get(0));
                        }
                    }
                }

                // To selection menu
                Intent intent = new Intent(Cart.this, CustomerAccount.class);
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        back = (ImageButton) findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go back
                Intent intent = new Intent(Cart.this, shopCategory.class);
                intent.putExtra("userID", userID);
                intent.putExtra("storeID", storeID);
                startActivity(intent);
            }
        });
    }



}
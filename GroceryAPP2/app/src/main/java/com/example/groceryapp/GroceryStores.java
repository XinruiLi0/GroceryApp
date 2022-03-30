package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.groceryapp.HelperClasses.MyAdapter;
import com.example.groceryapp.HelperClasses.StoreHelperClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GroceryStores extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ImageButton Share1;
    ImageButton Map1;

    ImageButton store1;
    Button Share2; //may need to change to ImageButton later

    private String userID;
    private String userName;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_stores);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


        Share1 = (ImageButton) findViewById(R.id.storeShare1);
        Map1 = (ImageButton) findViewById(R.id.StoreLocation1);
        store1 = (ImageButton) findViewById(R.id.Store1);
        Share2 = (Button) findViewById(R.id.storeShare2); //may need to change to ImageButton later

        // Extract user name from local
        Intent i = getIntent();
        userID = i.getStringExtra("userID");
        userName = i.getStringExtra("userName");
// 跳到后一个页面时不仅要传这2个，也要传store id

        // Request store list from db
        ArrayList<ArrayList<String>> result = DBUtil.Query("select id, StoreName from Retailers");

        // Show the stores in view
        recyclerView = findViewById(R.id.storeRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<StoreHelperClass> locations = new ArrayList<>();

        locations.add(new StoreHelperClass("Costco"));
        locations.add(new StoreHelperClass("Loblaw"));

        adapter = new MyAdapter(locations);
        recyclerView.setAdapter(adapter);

        Share1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Share this store");
                intent.setType("text/plain");
                startActivity(intent);

            }
        });

        Map1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,Map.class);
                startActivity(intent);

            }
        });
        store1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,shopCategory.class);

                startActivity(intent);

            }
        });

        Share2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // To selection menu
                Intent intent = new Intent(GroceryStores.this,Cart.class);
                startActivity(intent);

            }
        });

//        for (int i = 0; i < result.size(); ++i) {
//            LinearLayout store = new LinearLayout(this);
//            store.setBackgroundResource(R.drawable.btn_4);
//            store.setOrientation(LinearLayout.HORIZONTAL);
//            LinearLayout.LayoutParams p1 = new LayoutParams(dpToPx(380), dpToPx(110));
//            p1.gravity = Gravity.CENTER;
//            p1.setMargins(0, 0, 0, dpToPx(10));
//            store.setLayoutParams(p1);
//
//            TextView name = new TextView(this);
//            name.setText(result.get(i).get(1));
//            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 34.0F);
//            name.setTextColor(Color.parseColor("#2E5418"));
//            name.setTypeface(null, Typeface.BOLD_ITALIC);
//            LinearLayout.LayoutParams p2 = new LayoutParams(dpToPx(300), dpToPx(108));
//            p2.gravity = Gravity.CENTER;
//            p2.setMargins(0, dpToPx(5), 0, 0);
//            name.setLayoutParams(p2);
//
//            LinearLayout buttons = new LinearLayout(this);
//            buttons.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
//            store.setOrientation(LinearLayout.VERTICAL);
//            LinearLayout.LayoutParams p3 = new LayoutParams(dpToPx(40), dpToPx(100));
//            p3.gravity = Gravity.RIGHT;
//            p3.weight = 1;
//            p3.setMargins(0, dpToPx(5), dpToPx(30), dpToPx(5));
//            buttons.setLayoutParams(p3);
//
//            ImageButton next = new ImageButton(this);
//            next.setBackgroundColor(Color.parseColor("#00FFFFFF"));
//            next.setScaleType(ImageView.ScaleType.FIT_END);
//            next.setImageResource(R.drawable.next);
//            LinearLayout.LayoutParams p4 = new LayoutParams(dpToPx(28), dpToPx(28));
//            next.setLayoutParams(p4);
//
//            ImageButton map = new ImageButton(this);
//            map.setBackgroundColor(Color.parseColor("#00FFFFFF"));
//            map.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            map.setImageResource(R.drawable.map2);
//            LinearLayout.LayoutParams p5 = new LayoutParams(dpToPx(40), dpToPx(30));
//            map.setLayoutParams(p5);
//
//            ImageButton share = new ImageButton(this);
//            share.setBackgroundColor(Color.parseColor("#00FFFFFF"));
//            share.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//            share.setImageResource(R.drawable.share);
//            LinearLayout.LayoutParams p6 = new LayoutParams(dpToPx(30), dpToPx(30));
//            share.setLayoutParams(p6);
//
//            buttons.addView(next);
//            buttons.addView(map);
//            buttons.addView(share);
//            store.addView(name);
//            store.addView(buttons);
//            container.addView(store);
//        }
    }




    // navigation view
//    CustomerAccount customerAccount = new CustomerAccount();
//    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
//                Intent homeIntent = new Intent(GroceryStores.this, GroceryStores.class);
//                startActivity(homeIntent);
                return true;

            // jump to account page
            case R.id.account:
                Intent accountIntent = new Intent(GroceryStores.this, CustomerAccount.class);
                startActivity(accountIntent);
                return true;
        }

        return false;
    }


}
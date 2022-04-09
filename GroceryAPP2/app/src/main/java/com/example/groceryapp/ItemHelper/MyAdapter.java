package com.example.groceryapp.ItemHelper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Map;
import com.example.groceryapp.R;
import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.example.groceryapp.Cart;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<ItemHelperClass> locations;

    public MyAdapter(ArrayList<ItemHelperClass> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemHelperClass itemHelperClass = locations.get(position);
        holder.itemName.setText(itemHelperClass.getItemName());
        holder.itemId = itemHelperClass.getItemId();
        holder.itemStock = itemHelperClass.getItemStock();
        holder.restockTime = itemHelperClass.getRestockTime();
        holder.itemPrice.setText("$ " +itemHelperClass.getItemPrice());
        holder.itemCategory = itemHelperClass.getItemCategory();
        ArrayList<Integer> itemList = new ArrayList<Integer> ();
        itemList.add(R.drawable.costco);
        itemList.add(R.drawable.cucumber);
        itemList.add(R.drawable.blueberry);
        itemList.add(R.drawable.eggs);
        itemList.add(R.drawable.garlic);
        int imageIndex = Integer.parseInt(itemHelperClass.getItemImage());
        holder.itemImage.setImageResource(itemList.get(imageIndex));
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        String itemId;
        String itemStock;
        String restockTime;
        TextView itemPrice;
        String itemCategory;
        ImageView itemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}


package com.example.groceryapp.CartHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.groceryapp.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<CartHelperClass> locations;

    public MyAdapter(ArrayList<CartHelperClass> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_card_view, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartHelperClass cartHelperClass = locations.get(position);
        holder.itemName.setText(cartHelperClass.getItemName());
        holder.itemId = cartHelperClass.getItemId();
        holder.itemStock = cartHelperClass.getItemStock();
        holder.restockTime = cartHelperClass.getRestockTime();
        holder.itemPrice.setText("$ " +cartHelperClass.getItemPrice());
        holder.itemCategory = cartHelperClass.getItemCategory();
        ArrayList<Integer> itemList = new ArrayList<Integer> ();
        itemList.add(R.drawable.costco);
        itemList.add(R.drawable.cucumber);
        itemList.add(R.drawable.blueberry);
        itemList.add(R.drawable.eggs);
        itemList.add(R.drawable.garlic);
        int imageIndex = Integer.parseInt(cartHelperClass.getItemImage());
        holder.itemImage.setImageResource(itemList.get(imageIndex));
        holder.itemAmount.setText("x " +cartHelperClass.getItemAmount());
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
        TextView itemAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemAmount = itemView.findViewById(R.id.amount);
        }
    }
}

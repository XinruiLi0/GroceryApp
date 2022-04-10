package com.example.groceryapp.StoreProductHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.example.groceryapp.R;
import com.example.groceryapp.StoreHelper.StoreHelperClass;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<StoreProductHelperClass> locations;

    public MyAdapter(ArrayList<StoreProductHelperClass> locations) {
        this.locations = locations;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.store_product_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreProductHelperClass storeProductHelperClass = locations.get(position);
        holder.itemName.setText(storeProductHelperClass.getItemName());
        holder.itemId = storeProductHelperClass.getItemId();
        holder.itemStock = storeProductHelperClass.getItemStock();
        holder.restockTime = storeProductHelperClass.getRestockTime();
        holder.itemPrice.setText("$ " + storeProductHelperClass.getItemPrice());
        holder.itemCategory = storeProductHelperClass.getItemCategory();
//        ArrayList<Integer> itemList = new ArrayList<Integer> ();
//        itemList.add(R.drawable.costco);
//        itemList.add(R.drawable.cucumber);
//        itemList.add(R.drawable.blueberry);
//        itemList.add(R.drawable.eggs);
//        itemList.add(R.drawable.garlic);
//        int imageIndex = Integer.parseInt(itemHelperClass.getItemImage());
//        holder.itemImage.setImageResource(itemList.get(imageIndex));
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
//        ImageView itemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.productName);
            itemPrice = itemView.findViewById(R.id.productPrice);
//            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}

package com.example.groceryapp.CartHelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.groceryapp.R;

import java.io.ByteArrayOutputStream;
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
        View view = inflater.inflate(R.layout.cart_card_view, parent, false);
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
        holder.itemImage.setImageBitmap(convertStringToBitImage(cartHelperClass.getItemImage()));
        holder.itemAmount.setText("x " +cartHelperClass.getItemAmount());
    }

    // this function is used to decode the string into image
    public Bitmap convertStringToBitImage(String imageString){
        // decode string to image
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
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

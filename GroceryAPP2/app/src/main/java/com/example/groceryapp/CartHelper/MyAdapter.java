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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
        loadImage(holder, cartHelperClass.getItemImage());
        holder.itemAmount.setText("x " +cartHelperClass.getItemAmount());
    }

    public void loadImage(MyViewHolder holder, String fileName) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + fileName);
        try {
            final File localFile = File.createTempFile(fileName, "image");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    holder.itemImage.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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

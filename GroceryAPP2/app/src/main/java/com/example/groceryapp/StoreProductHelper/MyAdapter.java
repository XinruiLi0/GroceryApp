package com.example.groceryapp.StoreProductHelper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.R;
import com.example.groceryapp.UpdateProduct;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
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
        String itemId = storeProductHelperClass.getItemId();
        String itemName = storeProductHelperClass.getItemName();
        String itemStock = storeProductHelperClass.getItemStock();
        String restockTime = storeProductHelperClass.getRestockTime();
        String itemPrice = storeProductHelperClass.getItemPrice();
        String itemCategory = storeProductHelperClass.getItemCategory();

        holder.itemName.setText(itemName);
        holder.itemPrice.setText("$ " + itemPrice);
        loadImage(holder, storeProductHelperClass.getItemImage());

        // jump to update page if the item was clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateProduct.class);
                intent.putExtra("itemId", itemId);
                intent.putExtra("itemName", itemName);
                intent.putExtra("itemCategory", itemCategory);
                intent.putExtra("itemPrice", itemPrice);
                intent.putExtra("itemStock", itemStock);
                intent.putExtra("restockTime", restockTime);
                view.getContext().startActivity(intent);
            }
        });
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
        TextView itemPrice;
        ImageView itemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.productName);
            itemPrice = itemView.findViewById(R.id.productPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
        }
    }
}

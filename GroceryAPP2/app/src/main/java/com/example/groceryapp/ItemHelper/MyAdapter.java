package com.example.groceryapp.ItemHelper;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Map;
import com.example.groceryapp.R;
import com.example.groceryapp.ItemHelper.ItemHelperClass;
import com.example.groceryapp.Cart;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
        if (Double.parseDouble(holder.itemStock) < 1) {
            holder.itemQuantities.setText("Out of Stock");
            holder.itemQuantities.setTextColor(Color.RED);
            holder.itemNumber.setFocusable(false);
        }
        holder.itemPrice.setText("$ " +itemHelperClass.getItemPrice());
        holder.itemCategory = itemHelperClass.getItemCategory();
        loadImage(holder, itemHelperClass.getItemImage());
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
        TextView itemQuantities;
        EditText itemNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemQuantities = itemView.findViewById(R.id.quantities);
            itemNumber = itemView.findViewById(R.id.number);

            itemQuantities.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "The next restock time for "+itemName.getText()+" is "+restockTime, LENGTH_LONG).show();
                }
            });

            itemNumber.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    if (s.toString().isEmpty()) { return; }
                    int temp = Integer.parseInt(s.toString());
                    if (temp > Double.parseDouble(itemStock)) {
                        Toast.makeText(itemView.getContext(), "Your amount exceed the item storage, the storage for "+itemName.getText()+" is "+itemStock, LENGTH_LONG).show();
                        s.clear();
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
    }
}


package com.example.groceryapp.HelperClasses;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.R;
import com.example.groceryapp.shopCategory;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<StoreHelperClass> locations;

    public MyAdapter(ArrayList<StoreHelperClass> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.store_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StoreHelperClass storeHelperClass = locations.get(position);
        holder.storeName.setText(storeHelperClass.getStoreName());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView storeName;
        ImageButton next;
        ImageButton map;
        ImageButton share;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            storeName = itemView.findViewById(R.id.storeName);
            next = itemView.findViewById(R.id.next);
            map = itemView.findViewById(R.id.map);
            share = itemView.findViewById(R.id.share);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), shopCategory.class));
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next.getContext().startActivity(new Intent(next.getContext(), shopCategory.class));
                }
            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    map.getContext().startActivity(new Intent(map.getContext(), shopCategory.class));
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    share.getContext().startActivity(new Intent(share.getContext(), shopCategory.class));
                }
            });


        }
    }
}

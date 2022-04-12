package com.example.groceryapp.StoreHelper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.Map;
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
        holder.storeId.setText(storeHelperClass.getStoreId());
        holder.userId = storeHelperClass.getUserId();
        holder.userName = storeHelperClass.getUserName();
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        String userId;
        String userName;

        TextView storeName;
        TextView storeId;
        ImageButton next;
        ImageButton map;
        ImageButton share;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            storeName = itemView.findViewById(R.id.storeName);
            storeId = itemView.findViewById(R.id.storeId);
            next = itemView.findViewById(R.id.next);
            map = itemView.findViewById(R.id.map);
            share = itemView.findViewById(R.id.share);

            storeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), shopCategory.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userID", userId);
                    intent.putExtra("storeName", (String) storeName.getText());
                    intent.putExtra("storeID", (String) storeId.getText());
                    view.getContext().startActivity(intent);
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), shopCategory.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userID", userId);
                    intent.putExtra("storeName", (String) storeName.getText());
                    intent.putExtra("storeID", (String) storeId.getText());
                    view.getContext().startActivity(intent);
                }
            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Map.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userID", userId);
                    intent.putExtra("storeName", (String) storeName.getText());
                    intent.putExtra("storeID", (String) storeId.getText());
                    view.getContext().startActivity(intent);
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,"Share this store");
                    intent.setType("text/plain");
                    view.getContext().startActivity(intent);
                }
            });


        }
    }
}

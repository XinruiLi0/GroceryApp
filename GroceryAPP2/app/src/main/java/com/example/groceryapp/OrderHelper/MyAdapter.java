package com.example.groceryapp.OrderHelper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryapp.HistoryOrder;
import com.example.groceryapp.Map;
import com.example.groceryapp.R;
import com.example.groceryapp.OrderHelper.OrderHelperClass;
import com.example.groceryapp.shopCategory;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<OrderHelperClass> locations;

    public MyAdapter(ArrayList<OrderHelperClass> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderHelperClass orderHelperClass = locations.get(position);
        holder.id = orderHelperClass.getId();
        holder.orderNumber.setText("Order Number: "+orderHelperClass.getOrderNumber());
        holder.orderDate.setText("Order Date: "+orderHelperClass.getOrderDate());
        holder.name = orderHelperClass.getName();
        holder.phoneNumber = orderHelperClass.getPhoneNumber();
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        String id;
        LinearLayout order;
        ImageButton next;
        TextView orderNumber;
        TextView orderDate;
        String name;
        String phoneNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            order = itemView.findViewById(R.id.order);
            next = itemView.findViewById(R.id.next);
            orderNumber = itemView.findViewById(R.id.orderNum);
            orderDate = itemView.findViewById(R.id.orderDate);

            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), HistoryOrder.class);
                    intent.putExtra("id", id);
                    intent.putExtra("orderNumber", ((String) orderNumber.getText()).split("Order Number: ")[1]);
                    intent.putExtra("name", name);
                    intent.putExtra("phoneNumber", phoneNumber);
                    view.getContext().startActivity(intent);
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), HistoryOrder.class);
                    intent.putExtra("id", id);
                    intent.putExtra("orderNumber", ((String) orderNumber.getText()).split("Order Number: ")[1]);
                    intent.putExtra("name", name);
                    intent.putExtra("phoneNumber", phoneNumber);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}

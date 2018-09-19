package com.example.admin.retrofitsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.retrofitsample.MainActivity;
import com.example.admin.retrofitsample.OnClickInterface;
import com.example.admin.retrofitsample.R;
import com.example.admin.retrofitsample.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private List<Order> orders;
    MainActivity mainActivity;
    OnClickInterface onClickInterface;

    public OrderAdapter(MainActivity mainActivity, ArrayList<Order> orders,OnClickInterface onClickInterface) {
        this.mainActivity=mainActivity;
        this.onClickInterface=onClickInterface;
        this.orders=orders;
    }

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        holder.title.setText(orders.get(position).getOrder_cname());
        holder.status.setText(orders.get(position).getOrder_ostatus_name());
        holder.code.setText(orders.get(position).getOrder_code());
        holder.date_time.setText(orders.get(position).getOrder_datetime());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView status;
        TextView code;
        TextView package_name;
        TextView date_time;

        public ViewHolder(View v) {
            super(v);

            title =  v.findViewById(R.id.name);
            status =  v.findViewById(R.id.status);
            code =  v.findViewById(R.id.code);
            package_name =  v.findViewById(R.id.package_name);
            date_time= v.findViewById(R.id.date_time);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickInterface.onClick(getAdapterPosition());
                }
            });
        }
    }
}

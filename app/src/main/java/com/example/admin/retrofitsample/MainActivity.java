package com.example.admin.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.retrofitsample.adapter.OrderAdapter;
import com.example.admin.retrofitsample.model.Order;
import com.example.admin.retrofitsample.model.OrderResponce;
import com.example.admin.retrofitsample.retrofit.ApiClient;
import com.example.admin.retrofitsample.retrofit.IResponseListener;
import com.example.admin.retrofitsample.retrofit.RetrofitUtils;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IResponseListener, OnClickInterface {

    RecyclerView recyclerView;
    ArrayList<Order> all_my_feedbacks;
    OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycle();
        callRetro();

    }

    private void initRecycle() {

        recyclerView = findViewById(R.id.recyclerView);

    }

    private void callRetro() {
        RetrofitUtils.getInstance().retrofitEnqueue(
                ApiClient.getApiInterface()
                        .getOrderDetails(), MainActivity.this, 1);

    }


    @Override
    public void onSuccess(String response, int who) {
        if (who == 1) {
            OrderResponce order = new Gson().fromJson(response, OrderResponce.class);
            all_my_feedbacks = new ArrayList<>();
            all_my_feedbacks.addAll(order.getOrderList());
            setAdapter(all_my_feedbacks);

        }

    }

    private void setAdapter(ArrayList<Order> all_my_feedbacks) {
        orderAdapter = new OrderAdapter(this, all_my_feedbacks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(orderAdapter);
    }


    @Override
    public void onFailure(String response, int who, int errorCode) {

    }

    @Override
    public void onClick(int position) {

        Toast.makeText(this, "hai", Toast.LENGTH_SHORT).show();

    }
}

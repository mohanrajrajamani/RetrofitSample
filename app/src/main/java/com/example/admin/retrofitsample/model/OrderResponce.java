package com.example.admin.retrofitsample.model;

import java.util.ArrayList;

public class OrderResponce
{
    private ArrayList<Order> order_list;
    private int success;
    private String message;

    public ArrayList<Order> getOrderList() {
        return this.order_list;
    }

    public void setOrderList(ArrayList<Order> order_list) {
        this.order_list = order_list;
    }



    public int getSuccess() {
        return this.success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }



    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

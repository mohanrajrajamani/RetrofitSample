package com.example.admin.retrofitsample.model;

public class Order {
    private String order_id;
    private String order_code;
    private String order_cname;
    private String order_package_name;
    private String order_datetime;
    private String order_ostatus_name;

    public Order() {
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getOrder_cname() {
        return order_cname;
    }

    public void setOrder_cname(String order_cname) {
        this.order_cname = order_cname;
    }

    public String getOrder_package_name() {
        return order_package_name;
    }

    public void setOrder_package_name(String order_package_name) {
        this.order_package_name = order_package_name;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public String getOrder_ostatus_name() {
        return order_ostatus_name;
    }

    public void setOrder_ostatus_name(String order_ostatus_name) {
        this.order_ostatus_name = order_ostatus_name;
    }
}




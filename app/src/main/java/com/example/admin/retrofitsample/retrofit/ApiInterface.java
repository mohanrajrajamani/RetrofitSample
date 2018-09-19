package com.example.admin.retrofitsample.retrofit;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("getorderdetails.php")
    Call<ResponseBody> getOrderDetails();
}

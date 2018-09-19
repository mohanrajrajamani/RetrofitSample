package com.example.admin.retrofitsample.retrofit;

public interface IResponseListener {

    void onSuccess(String response, int who);

    void onFailure(String response, int who, int errorCode);

}

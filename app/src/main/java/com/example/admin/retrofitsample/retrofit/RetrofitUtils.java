package com.example.admin.retrofitsample.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitUtils {

    private static String TAG = RetrofitUtils.class.getSimpleName();
    private static RetrofitUtils instance;

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            instance =  new RetrofitUtils();
        }
        return instance;
    }

    private String getStringFromByte(InputStream paramInputStream) {
        StringBuilder localStringBuilder = new StringBuilder();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        try {
            while (true) {
                String str = localBufferedReader.readLine();
                if (str == null)
                    break;
                localStringBuilder.append(str);
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localStringBuilder.toString();
    }

    public void retrofitEnqueue(Call<ResponseBody> call, final IResponseListener listener, final int who) {
        call.enqueue(
                new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        Log.d(TAG, "onResponse: response.code() "+ response.code() +
                                " raw code "+ response.raw().code() +
                                "raw "+ response.raw());

                        String responseString = null;
                        if(response.body() != null && response.body().byteStream() != null){
                            responseString =  getStringFromByte(response.body().byteStream());
                        }

                        if(listener == null){
                            return;
                        }

                        switch (response.code()){
                            case 200:
                                //ok
                                try {
                                    // convert String to JSON to get status of the response
                                    JSONObject jsonObject = new JSONObject(responseString);
                                    if(jsonObject.has("success")){
                                        if(jsonObject.getInt("success") == 1){
                                            //success
                                            listener.onSuccess(responseString, who);
                                        }else{
                                            //failed
                                            if(jsonObject.has("message")){
                                                listener.onFailure(jsonObject.getString("message"), who, 0);
                                            }else{
                                                //not a server error! so 0 for who
                                                listener.onFailure(responseString, who, 0);
                                            }

                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 201:
                                //created
                                listener.onSuccess(responseString, who);
                                break;
                            case 400:
                                listener.onFailure(responseString, who,400);
                                break;
                            case 401:
                                //unauthorised
                                listener.onFailure(responseString, who,401);
                                break;
                            case 403:
                                //forbitten
                                listener.onFailure(responseString, who,403);
                                break;
                            case 404:
                                //not found
                                listener.onFailure(responseString, who,404);
                            case 500:
                                //server error
                                listener.onFailure(responseString, who,500);
                            default:
                                //unknown error
                                listener.onFailure(responseString, who,response.code());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        //not a server error! so 0 for who
                        listener.onFailure(t.getLocalizedMessage(), who, 0);
                    }
                }
        );
    }

}

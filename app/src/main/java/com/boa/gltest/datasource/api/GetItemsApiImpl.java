package com.boa.gltest.datasource.api;

import com.boa.gltest.BuildConfig;
import com.boa.gltest.datasource.api.retrofit.GetItemsRequest;
import com.boa.gltest.global.model.Item;
import com.boa.gltest.usecase.GetItems;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetItemsApiImpl implements GetItems, Callback<List<Item>> {
    private Listener listener = new NullListener();
    public int timeout = 50;

    @Override
    public List<Item> get() {
        return null;
    }

    @Override
    public void getAsync(Listener listener) {
        if (listener != null) {
            this.listener = listener;
        }

        OkHttpClient client;

        if (BuildConfig.ShowLogs) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS).build();
        } else {
            client = new OkHttpClient.Builder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS).build();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://private-f0eea-mobilegllatam.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        GetItemsRequest request = retrofit.create(GetItemsRequest.class);
        Call<List<Item>> call = request.getItems();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
        if (response.body() != null) {
            List<Item> items = new ArrayList<>();

            for (Item item : response.body()) {
                items.add(item);
            }

            listener.onItemsReceived(items, false);
        } else {
            listener.onError(new NullPointerException());
        }
    }

    @Override
    public void onFailure(Call<List<Item>> call, Throwable t) {
        listener.onError(new Exception(t));
    }
}

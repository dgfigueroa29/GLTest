package com.boa.gltest.datasource.api.retrofit;

import com.boa.gltest.global.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetItemsRequest {
    @GET("/list")
    Call<List<Item>> getItems();
}

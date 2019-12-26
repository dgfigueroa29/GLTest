package com.boa.gltest.datasource.api.retrofit;

import com.boa.gltest.datasource.api.GetItemsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetItemsRequest {
    @GET("/list")
    Call<GetItemsResponse> getItems();
}

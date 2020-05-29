package com.ranamulesoftapi.bilal.apis;

import com.ranamulesoftapi.bilal.models.MyDataList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("mocking/api/v1/links/c4326100-ed9e-4835-acf6-99435177eba0/json/sf-json")
    Call<MyDataList> ListingApi(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret
    );
}

package com.example.gambittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("39?page=1")
    Call<List<DishModel>> getProducts();
}

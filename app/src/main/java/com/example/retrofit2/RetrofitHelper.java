package com.example.retrofit2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/posts/1")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

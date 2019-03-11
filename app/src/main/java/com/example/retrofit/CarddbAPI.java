package com.example.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarddbAPI {
    @GET("/cards")
    Call<CardsList> getCards();
}
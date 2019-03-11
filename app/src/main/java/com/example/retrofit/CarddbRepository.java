package com.example.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarddbRepository {
    CarddbAPI carddbAPI;

    public CarddbRepository(){
        carddbAPI = CarddbModule.getAPI();
    }

    public LiveData<List<Card>> getCards(){
        final MutableLiveData<List<Card>> lista = new MutableLiveData<>();

        carddbAPI.getCards().enqueue(new Callback<CardsList>() {
            @Override
            public void onResponse(Call<CardsList> call, Response<CardsList> response) {
                lista.setValue(response.body().Basic);
                lista.setValue(response.body().Classic);
            }

            @Override
            public void onFailure(Call<CardsList> call, Throwable t) {
            }
        });

        return lista;
    }
}
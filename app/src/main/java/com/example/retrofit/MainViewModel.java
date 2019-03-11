package com.example.retrofit;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private CarddbRepository carddbRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        carddbRepository = new CarddbRepository();
    }

    public LiveData<List<Card>> getCards(){
        return carddbRepository.getCards();
    }
}
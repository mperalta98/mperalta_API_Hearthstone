package com.example.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private CardListAdapter mCardListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.cardList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCardListAdapter = new CardListAdapter();
        mRecyclerView.setAdapter(mCardListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getCards().observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(@Nullable List<Card> cards) {
                mCardListAdapter.cardList = cards;
                mCardListAdapter.notifyDataSetChanged();
            }

        });
    }
}
package com.example.retrofit;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardListViewHolder>{
    public List<Card> cardList = new ArrayList<>();

    @NonNull
    @Override
    public CardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListViewHolder holder, int position) {
        Card card = cardList.get(position);

        holder.name.setText(card.name);
        Log.e("IMG", "==>" + card.img);
        GlideApp.with(holder.itemView.getContext()).load(card.img).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class CardListViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView poster;
        public CardListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cardName);
            poster = itemView.findViewById(R.id.cardImage);
        }
    }
}
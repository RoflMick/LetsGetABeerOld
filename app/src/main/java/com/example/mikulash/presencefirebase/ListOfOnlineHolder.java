package com.example.mikulash.presencefirebase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ListOfOnlineHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView textEmail;
    ItemClickListener itemClickListener;

    public ListOfOnlineHolder(View itemView) {
        super(itemView);

        textEmail = itemView.findViewById(R.id.textEmail);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}

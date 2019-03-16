package com.example.android.myweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by raj garg on 21-12-2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyPlacesViewHolder>{

    private Context mctx;
    private List<UUrl> MyPlacesList;

    public Adapter(History mctx, List<UUrl> myPlacesList) {
        this.mctx = mctx;
        MyPlacesList = myPlacesList;
    }

    @Override
    public MyPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.layout_url, parent,false);
        MyPlacesViewHolder holder = new MyPlacesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyPlacesViewHolder holder, int position) {
        UUrl myPlaces = MyPlacesList.get(position);
        holder.textUrl.setText(myPlaces.getUrl());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                {
                    Toast.makeText(mctx,"Long Click:"+MyPlacesList.get(position),Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return MyPlacesList.size();
    }

    class MyPlacesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView textUrl;
        private ItemClickListener itemClickListener;
        public MyPlacesViewHolder(View itemView) {
            super(itemView);
            textUrl = itemView.findViewById(R.id.layout);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            return true;
        }
    }
}


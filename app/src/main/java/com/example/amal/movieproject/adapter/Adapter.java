package com.example.amal.movieproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amal.movieproject.MainActivity;
import com.example.amal.movieproject.R;
import com.example.amal.movieproject.fimdetails;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by emyahmed_96 on 7/19/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
    Context context;
    List<com.example.amal.movieproject.model.Movie> movie;


    public Adapter(Context c , List<com.example.amal.movieproject.model.Movie> m)
    {
        this.context=c;
        this.movie=m;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.item ,parent ,false);
        Holder myHolder=new Holder(row);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position)
    {
        String s = movie.get(position).getName();
        String imageURL = movie.get(position).getPosterPath();
        Picasso.with(context).load(imageURL).into(holder.beauty);

    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView beauty;
        public Holder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            beauty=(ImageView)itemView.findViewById(R.id.photo);
        }

        @Override
        public void onClick(View v)
        {
            Intent i = new Intent(context,fimdetails.class) ;
            i.putExtra("movieClicked",movie.get(getLayoutPosition()));
            context.startActivity(i);
        }
    }

}


package com.example.anambasgo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder  {
    View mView;
    public ViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
    }
    public void setDetails(Context ctx, String image1, String title, String populasi,String koordinat, String key, String koorlat, String koorlong
            ,String hotel1, String hotel2, String hotel3,
                           String image2, String image3, String image4, String image5){
        ImageView mImageIv = mView.findViewById(R.id.image1);
        TextView titleTv = mView.findViewById(R.id.titleTv);
        TextView koordinatTv = mView.findViewById(R.id.koordinatTv);
        TextView keyTv = mView.findViewById(R.id.keyTv);
        TextView koorlatTv = mView.findViewById(R.id.koorlatTv);
        TextView koorlongTv = mView.findViewById(R.id.koorlongTv);
        TextView posisiTv = mView.findViewById(R.id.posisiTv);
        ImageView hotelIv1 = mView.findViewById(R.id.hotelIv1);
        ImageView hotelIv2 = mView.findViewById(R.id.hotelIv2);
        ImageView hotelIv3 = mView.findViewById(R.id.hotelIv3);
        ImageView descIv2 = mView.findViewById(R.id.descIv2);
        ImageView descIv3 = mView.findViewById(R.id.descIv3);
        ImageView descIv4 = mView.findViewById(R.id.descIv4);
        ImageView descIv5 = mView.findViewById(R.id.descIv5);
        titleTv.setText(title);
        posisiTv.setText(populasi);
        koordinatTv.setText(koordinat);
        koorlatTv.setText(koorlat);
        koorlongTv.setText(koorlong);
        keyTv.setText(key);
        Picasso.get().load(image1).into(mImageIv);
        Picasso.get().load(hotel1).into(hotelIv1);
        Picasso.get().load(hotel2).into(hotelIv2);
        Picasso.get().load(hotel3).into(hotelIv3);
        Picasso.get().load(image2).into(descIv2);
        Picasso.get().load(image3).into(descIv3);
        Picasso.get().load(image4).into(descIv4);
        Picasso.get().load(image5).into(descIv5);

    }
    private ViewHolder.ClickListener mClickListener;
    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

    }

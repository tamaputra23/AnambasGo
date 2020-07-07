package com.example.anambasgo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class UnpopulatedActivity extends BaseActivity {
    RecyclerView recyclerView;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpopulated);
        myRef = FirebaseDatabase.getInstance().getReference().child("Data");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
    }
    @Override
    public void onStart() {
        super.onStart();
        Query query = myRef.orderByChild("populasi").equalTo("0");
        FirebaseRecyclerAdapter<model, UnpopulatedActivity.UnpopulatedViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model, UnpopulatedActivity.UnpopulatedViewHolder>(
                        model.class,
                        R.layout.cardview,
                        UnpopulatedActivity.UnpopulatedViewHolder.class,
                        query
                ) {
                    @Override
                    protected void populateViewHolder(UnpopulatedActivity.UnpopulatedViewHolder viewHolder, model model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getImage1(), model.getTitle(), model.getPopulasi(), model.getKoordinat(), model.getKey(),
                                model.getKoorlat(), model.getKoorlong(),model.getImage2(), model.getImage3(), model.getImage4(), model.getImage5());
                    }
                    @Override
                    public UnpopulatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        UnpopulatedViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new UnpopulatedViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView mTitleTv = view.findViewById(R.id.titleTv);
                                TextView mKoordinatTv = view.findViewById(R.id.koordinatTv);
                                TextView mKeyTv = view.findViewById(R.id.keyTv);
                                ImageView mImageView = view.findViewById(R.id.image1);
                                TextView mKoorlatTv = view.findViewById(R.id.koorlatTv);
                                TextView mKoorlongTv = view.findViewById(R.id.koorlongTv);

                                ImageView descIv2 = view.findViewById(R.id.descIv2);
                                ImageView descIv3 = view.findViewById(R.id.descIv3);
                                ImageView descIv4 = view.findViewById(R.id.descIv4);
                                ImageView descIv5 = view.findViewById(R.id.descIv5);

                                //get data from views
                                String mTitle = mTitleTv.getText().toString();
                                Drawable mDrawable = mImageView.getDrawable();
                                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                Drawable mDrawable4 = descIv2.getDrawable();
                                Bitmap mBitmap4 = ((BitmapDrawable) mDrawable4).getBitmap();
                                Drawable mDrawable5 = descIv3.getDrawable();
                                Bitmap mBitmap5 = ((BitmapDrawable) mDrawable5).getBitmap();
                                Drawable mDrawable6 = descIv4.getDrawable();
                                Bitmap mBitmap6 = ((BitmapDrawable) mDrawable6).getBitmap();
                                Drawable mDrawable7 = descIv5.getDrawable();
                                Bitmap mBitmap7 = ((BitmapDrawable) mDrawable7).getBitmap();
                                String mKoordinat = mKoordinatTv.getText().toString();
                                String mKoorlat = mKoorlatTv.getText().toString();
                                String mKoorlong = mKoorlongTv.getText().toString();
                                String mKey = mKeyTv.getText().toString();


                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), PostUnpopulatedDetail.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] bytes = stream.toByteArray();
                                //gambar image2
                                ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                                mBitmap4.compress(Bitmap.CompressFormat.PNG, 50, stream4);
                                byte[] bytes4 = stream4.toByteArray();
                                //gambar image3
                                ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                                mBitmap5.compress(Bitmap.CompressFormat.PNG, 50, stream5);
                                byte[] bytes5 = stream5.toByteArray();
                                //gambar image4
                                ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                                mBitmap6.compress(Bitmap.CompressFormat.PNG, 50, stream6);
                                byte[] bytes6 = stream6.toByteArray();
                                //gambar image5
                                ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
                                mBitmap7.compress(Bitmap.CompressFormat.PNG, 50, stream7);
                                byte[] bytes7 = stream7.toByteArray();
                                intent.putExtra("image1", bytes); //put bitmap image as array of bytes
                                intent.putExtra("image2", bytes4);
                                intent.putExtra("image3", bytes5);
                                intent.putExtra("image4", bytes6);
                                intent.putExtra("image5", bytes7);
                                intent.putExtra("title", mTitle); // put title
                                intent.putExtra("koordinat", mKoordinat);
                                intent.putExtra("key",mKey);
                                intent.putExtra("koorlat",mKoorlat);
                                intent.putExtra("koorlong",mKoorlong);
                                startActivity(intent); //start activity
                                Intent intentmaps = new Intent(view.getContext(), MainActivity.class);
                                intentmaps.putExtra("title", mTitle);
                            }

                        });

                        return viewHolder;
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public static class UnpopulatedViewHolder extends RecyclerView.ViewHolder  {
        View mView;
        public UnpopulatedViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view, getAdapterPosition());
                }
            });

        }
        public void setDetails(Context ctx, String image1, String title, String populasi, String koordinat, String key, String koorlat, String koorlong,
                               String image2, String image3, String image4, String image5){
            ImageView mImageIv = mView.findViewById(R.id.image1);
            TextView titleTv = mView.findViewById(R.id.titleTv);
            TextView koordinatTv = mView.findViewById(R.id.koordinatTv);
            TextView keyTv = mView.findViewById(R.id.keyTv);
            TextView koorlatTv = mView.findViewById(R.id.koorlatTv);
            TextView koorlongTv = mView.findViewById(R.id.koorlongTv);
            ImageView descIv2 = mView.findViewById(R.id.descIv2);
            ImageView descIv3 = mView.findViewById(R.id.descIv3);
            ImageView descIv4 = mView.findViewById(R.id.descIv4);
            ImageView descIv5 = mView.findViewById(R.id.descIv5);

            titleTv.setText(title);
            koordinatTv.setText(koordinat);
            koorlatTv.setText(koorlat);
            koorlongTv.setText(koorlong);
            keyTv.setText(key);
            Picasso.get().load(image1).into(mImageIv);
            Picasso.get().load(image2).into(descIv2);
            Picasso.get().load(image3).into(descIv3);
            Picasso.get().load(image4).into(descIv4);
            Picasso.get().load(image5).into(descIv5);

        }
        private UnpopulatedViewHolder.ClickListener mClickListener;
        //interface to send callbacks
        public interface ClickListener{
            void onItemClick(View view, int position);
        }
        public void setOnClickListener(UnpopulatedViewHolder.ClickListener clickListener){
            mClickListener = clickListener;
        }

    }
    public void back (View view) {
        onBackPressed();
    }
}

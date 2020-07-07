package com.example.anambasgo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class AllPlacesActivity extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference myRef;
    SharedPreferences mSharedPref; //for saving sort settings
    FirebaseDatabase mFirebaseDatabase;
    LinearLayoutManager mLayoutManager;

    public AllPlacesActivity() {
    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.root, container, false);
        setHasOptionsMenu(true);

        getActivity().setTitle("AnambasGo");
        myRef = FirebaseDatabase.getInstance().getReference().child("Data");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new
                GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model, ViewHolder>(
                        model.class,
                        R.layout.cardview,
                        ViewHolder.class,
                        myRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, model model, int position) {
                        viewHolder.setDetails(getContext(),  model.getImage1(), model.getTitle(), model.getPopulasi(), model.getKoordinat(), model.getKey(),
                                model.getKoorlat(), model.getKoorlong(), model.getHotel1(), model.getHotel2(), model.getHotel3(),
                                model.getImage2(), model.getImage3(), model.getImage4(), model.getImage5());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mPopulasiTv = view.findViewById(R.id.posisiTv);
                                String populasi = mPopulasiTv.getText().toString();
                                if (populasi.equals("1")) {
                                    //Views
                                    TextView mTitleTv = view.findViewById(R.id.titleTv);
                                    TextView mKoordinatTv = view.findViewById(R.id.koordinatTv);
                                    TextView mKeyTv = view.findViewById(R.id.keyTv);
                                    ImageView mImageView = view.findViewById(R.id.image1);
                                    TextView mKoorlatTv = view.findViewById(R.id.koorlatTv);
                                    TextView mKoorlongTv = view.findViewById(R.id.koorlongTv);
                                    ImageView hotelIv1 = view.findViewById(R.id.hotelIv1);
                                    ImageView hotelIv2 = view.findViewById(R.id.hotelIv2);
                                    ImageView hotelIv3 = view.findViewById(R.id.hotelIv3);

                                    ImageView descIv2 = view.findViewById(R.id.descIv2);
                                    ImageView descIv3 = view.findViewById(R.id.descIv3);
                                    ImageView descIv4 = view.findViewById(R.id.descIv4);
                                    ImageView descIv5 = view.findViewById(R.id.descIv5);

                                    //get data from views
                                    String mTitle = mTitleTv.getText().toString();
                                    Drawable mDrawable = mImageView.getDrawable();
                                    Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                    Drawable mDrawable1 = hotelIv1.getDrawable();
                                    Bitmap mBitmap1 = ((BitmapDrawable) mDrawable1).getBitmap();
                                    Drawable mDrawable2 = hotelIv2.getDrawable();
                                    Bitmap mBitmap2 = ((BitmapDrawable) mDrawable2).getBitmap();
                                    Drawable mDrawable3 = hotelIv3.getDrawable();
                                    Bitmap mBitmap3 = ((BitmapDrawable) mDrawable3).getBitmap();
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
                                    Intent intent = new Intent(view.getContext(), PostPopulatedActivity.class);
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    byte[] bytes = stream.toByteArray();
                                    //hotel1
                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    mBitmap1.compress(Bitmap.CompressFormat.PNG, 50, stream1);
                                    byte[] bytes1 = stream1.toByteArray();
                                    //gambar hotel2
                                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                    mBitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream2);
                                    byte[] bytes2 = stream2.toByteArray();
                                    //gambar hotel3
                                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                                    mBitmap3.compress(Bitmap.CompressFormat.PNG, 50, stream3);
                                    byte[] bytes3 = stream3.toByteArray();
                                    //gambar image2
                                    ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                                    mBitmap4.compress(Bitmap.CompressFormat.JPEG, 50, stream4);
                                    byte[] bytes4 = stream4.toByteArray();
                                    //gambar image3
                                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                                    mBitmap5.compress(Bitmap.CompressFormat.JPEG, 50, stream5);
                                    byte[] bytes5 = stream5.toByteArray();
                                    //gambar image4
                                    ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                                    mBitmap6.compress(Bitmap.CompressFormat.JPEG, 50, stream6);
                                    byte[] bytes6 = stream6.toByteArray();
                                    //gambar image5
                                    ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
                                    mBitmap7.compress(Bitmap.CompressFormat.JPEG, 50, stream7);
                                    byte[] bytes7 = stream7.toByteArray();
                                    intent.putExtra("image1", bytes);//put bitmap image as array of bytes
                                    intent.putExtra("hotel1", bytes1);
                                    intent.putExtra("hotel2", bytes2);
                                    intent.putExtra("hotel3", bytes3);
                                    intent.putExtra("image2", bytes4);
                                    intent.putExtra("image3", bytes5);
                                    intent.putExtra("image4", bytes6);
                                    intent.putExtra("image5", bytes7);
                                    intent.putExtra("title", mTitle); // put title
                                    intent.putExtra("koordinat", mKoordinat);
                                    intent.putExtra("key", mKey);
                                    intent.putExtra("koorlat", mKoorlat);
                                    intent.putExtra("koorlong", mKoorlong);
                                    startActivity(intent); //start activity
                                    Intent intentmaps = new Intent(view.getContext(), MainActivity.class);
                                    intentmaps.putExtra("title", mTitle);
                                }
                                else if (populasi.equals("0")) {
                                    //Views
                                    TextView mTitleTv = view.findViewById(R.id.titleTv);
                                    TextView mKoordinatTv = view.findViewById(R.id.koordinatTv);
                                    TextView mKeyTv = view.findViewById(R.id.keyTv);
                                    ImageView mImageView = view.findViewById(R.id.image1);
                                    TextView mKoorlatTv = view.findViewById(R.id.koorlatTv);
                                    TextView mKoorlongTv = view.findViewById(R.id.koorlongTv);
                                    ImageView hotelIv1 = view.findViewById(R.id.hotelIv1);
                                    ImageView hotelIv2 = view.findViewById(R.id.hotelIv2);
                                    ImageView hotelIv3 = view.findViewById(R.id.hotelIv3);

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
                                    /**Drawable mDrawable1 = hotelIv1.getDrawable();
                                    Bitmap mBitmap1 = ((BitmapDrawable) mDrawable1).getBitmap();
                                    Drawable mDrawable2 = hotelIv2.getDrawable();
                                    Bitmap mBitmap2 = ((BitmapDrawable) mDrawable2).getBitmap();
                                    Drawable mDrawable3 = hotelIv3.getDrawable();
                                    Bitmap mBitmap3 = ((BitmapDrawable) mDrawable3).getBitmap();**/
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
                                    mBitmap4.compress(Bitmap.CompressFormat.JPEG, 50, stream4);
                                    byte[] bytes4 = stream4.toByteArray();
                                    //gambar image3
                                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                                    mBitmap5.compress(Bitmap.CompressFormat.JPEG, 50, stream5);
                                    byte[] bytes5 = stream5.toByteArray();
                                    //gambar image4
                                    ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                                    mBitmap6.compress(Bitmap.CompressFormat.JPEG, 50, stream6);
                                    byte[] bytes6 = stream6.toByteArray();
                                    //gambar image5
                                    ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
                                    Bitmap mBitmap7 = ((BitmapDrawable) mDrawable7).getBitmap();
                                    mBitmap7.compress(Bitmap.CompressFormat.JPEG, 50, stream7);
                                    byte[] bytes7 = stream7.toByteArray();

                                    /**hotel1
                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    mBitmap1.compress(Bitmap.CompressFormat.PNG, 50, stream1);
                                    byte[] bytes1 = stream1.toByteArray();
                                    //gambar hotel2
                                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                    mBitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream2);
                                    byte[] bytes2 = stream2.toByteArray();
                                    //gambar hotel3
                                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                                    mBitmap3.compress(Bitmap.CompressFormat.PNG, 50, stream3);
                                    byte[] bytes3 = stream3.toByteArray();**/
                                    intent.putExtra("image1", bytes);//put bitmap image as array of bytes
                                    //intent.putExtra("hotel1", bytes1);
                                    //intent.putExtra("hotel2", bytes2);
                                    //intent.putExtra("hotel3", bytes3);
                                    intent.putExtra("image2", bytes4);
                                    intent.putExtra("image3", bytes5);
                                    intent.putExtra("image4", bytes6);
                                    intent.putExtra("image5", bytes7);
                                    intent.putExtra("title", mTitle); // put title
                                    intent.putExtra("koordinat", mKoordinat);
                                    intent.putExtra("key", mKey);
                                    intent.putExtra("koorlat", mKoorlat);
                                    intent.putExtra("koorlong", mKoorlong);
                                    startActivity(intent); //start activity
                                    Intent intentmaps = new Intent(view.getContext(), MainActivity.class);
                                    intentmaps.putExtra("title", mTitle);
                                }
                            }

                        });

                        return viewHolder;
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public void firebaseSearch(String searchText) {
        String query = searchText.toLowerCase();
        Query firebaseSearchQuery = myRef.orderByChild("search").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerAdapter<model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<model, ViewHolder>(
                        model.class,
                        R.layout.cardview,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, model model, int position) {
                        viewHolder.setDetails(getContext(),  model.getImage1(), model.getTitle(), model.getPopulasi(), model.getKoordinat(), model.getKey(),
                                model.getKoorlat(), model.getKoorlong(), model.getHotel1(), model.getHotel2(), model.getHotel3(),
                                model.getImage2(), model.getImage3(), model.getImage4(), model.getImage5());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView mPopulasiTv = view.findViewById(R.id.posisiTv);
                                String populasi = mPopulasiTv.getText().toString();
                                if (populasi.equals("1")) {
                                    //Views
                                    TextView mTitleTv = view.findViewById(R.id.titleTv);
                                    TextView mKoordinatTv = view.findViewById(R.id.koordinatTv);
                                    TextView mKeyTv = view.findViewById(R.id.keyTv);
                                    ImageView mImageView = view.findViewById(R.id.image1);
                                    TextView mKoorlatTv = view.findViewById(R.id.koorlatTv);
                                    TextView mKoorlongTv = view.findViewById(R.id.koorlongTv);
                                    ImageView hotelIv1 = view.findViewById(R.id.hotelIv1);
                                    ImageView hotelIv2 = view.findViewById(R.id.hotelIv2);
                                    ImageView hotelIv3 = view.findViewById(R.id.hotelIv3);

                                    ImageView descIv2 = view.findViewById(R.id.descIv2);
                                    ImageView descIv3 = view.findViewById(R.id.descIv3);
                                    ImageView descIv4 = view.findViewById(R.id.descIv4);
                                    ImageView descIv5 = view.findViewById(R.id.descIv5);

                                    //get data from views
                                    String mTitle = mTitleTv.getText().toString();
                                    Drawable mDrawable = mImageView.getDrawable();
                                    Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                    Drawable mDrawable1 = hotelIv1.getDrawable();
                                    Bitmap mBitmap1 = ((BitmapDrawable) mDrawable1).getBitmap();
                                    Drawable mDrawable2 = hotelIv2.getDrawable();
                                    Bitmap mBitmap2 = ((BitmapDrawable) mDrawable2).getBitmap();
                                    Drawable mDrawable3 = hotelIv3.getDrawable();
                                    Bitmap mBitmap3 = ((BitmapDrawable) mDrawable3).getBitmap();
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
                                    Intent intent = new Intent(view.getContext(), PostPopulatedActivity.class);
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                    byte[] bytes = stream.toByteArray();
                                    //hotel1
                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    mBitmap1.compress(Bitmap.CompressFormat.PNG, 50, stream1);
                                    byte[] bytes1 = stream1.toByteArray();
                                    //gambar hotel2
                                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                    mBitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream2);
                                    byte[] bytes2 = stream2.toByteArray();
                                    //gambar hotel3
                                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                                    mBitmap3.compress(Bitmap.CompressFormat.PNG, 50, stream3);
                                    byte[] bytes3 = stream3.toByteArray();
                                    //gambar image2
                                    ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                                    mBitmap4.compress(Bitmap.CompressFormat.JPEG, 50, stream4);
                                    byte[] bytes4 = stream4.toByteArray();
                                    //gambar image3
                                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                                    mBitmap5.compress(Bitmap.CompressFormat.JPEG, 50, stream5);
                                    byte[] bytes5 = stream5.toByteArray();
                                    //gambar image4
                                    ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                                    mBitmap6.compress(Bitmap.CompressFormat.JPEG, 50, stream6);
                                    byte[] bytes6 = stream6.toByteArray();
                                    //gambar image5
                                    ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
                                    mBitmap7.compress(Bitmap.CompressFormat.JPEG, 50, stream7);
                                    byte[] bytes7 = stream7.toByteArray();
                                    intent.putExtra("image1", bytes);//put bitmap image as array of bytes
                                    intent.putExtra("hotel1", bytes1);
                                    intent.putExtra("hotel2", bytes2);
                                    intent.putExtra("hotel3", bytes3);
                                    intent.putExtra("image2", bytes4);
                                    intent.putExtra("image3", bytes5);
                                    intent.putExtra("image4", bytes6);
                                    intent.putExtra("image5", bytes7);
                                    intent.putExtra("title", mTitle); // put title
                                    intent.putExtra("koordinat", mKoordinat);
                                    intent.putExtra("key", mKey);
                                    intent.putExtra("koorlat", mKoorlat);
                                    intent.putExtra("koorlong", mKoorlong);
                                    startActivity(intent); //start activity
                                    Intent intentmaps = new Intent(view.getContext(), MainActivity.class);
                                    intentmaps.putExtra("title", mTitle);
                                }
                                else if (populasi.equals("0")) {
                                    //Views
                                    TextView mTitleTv = view.findViewById(R.id.titleTv);
                                    TextView mKoordinatTv = view.findViewById(R.id.koordinatTv);
                                    TextView mKeyTv = view.findViewById(R.id.keyTv);
                                    ImageView mImageView = view.findViewById(R.id.image1);
                                    TextView mKoorlatTv = view.findViewById(R.id.koorlatTv);
                                    TextView mKoorlongTv = view.findViewById(R.id.koorlongTv);
                                    ImageView hotelIv1 = view.findViewById(R.id.hotelIv1);
                                    ImageView hotelIv2 = view.findViewById(R.id.hotelIv2);
                                    ImageView hotelIv3 = view.findViewById(R.id.hotelIv3);

                                    ImageView descIv2 = view.findViewById(R.id.descIv2);
                                    ImageView descIv3 = view.findViewById(R.id.descIv3);
                                    ImageView descIv4 = view.findViewById(R.id.descIv4);
                                    ImageView descIv5 = view.findViewById(R.id.descIv5);

                                    //get data from views
                                    String mTitle = mTitleTv.getText().toString();
                                    Drawable mDrawable = mImageView.getDrawable();
                                    Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                                    /**Drawable mDrawable1 = hotelIv1.getDrawable();
                                    Bitmap mBitmap1 = ((BitmapDrawable) mDrawable1).getBitmap();
                                    Drawable mDrawable2 = hotelIv2.getDrawable();
                                    Bitmap mBitmap2 = ((BitmapDrawable) mDrawable2).getBitmap();
                                    Drawable mDrawable3 = hotelIv3.getDrawable();
                                    Bitmap mBitmap3 = ((BitmapDrawable) mDrawable3).getBitmap();**/
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
                                    mBitmap4.compress(Bitmap.CompressFormat.JPEG, 50, stream4);
                                    byte[] bytes4 = stream4.toByteArray();
                                    //gambar image3
                                    ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
                                    mBitmap5.compress(Bitmap.CompressFormat.JPEG, 50, stream5);
                                    byte[] bytes5 = stream5.toByteArray();
                                    //gambar image4
                                    ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
                                    mBitmap6.compress(Bitmap.CompressFormat.JPEG, 50, stream6);
                                    byte[] bytes6 = stream6.toByteArray();
                                    //gambar image5
                                    ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
                                    mBitmap7.compress(Bitmap.CompressFormat.JPEG, 50, stream7);
                                    byte[] bytes7 = stream7.toByteArray();
                                    /**hotel1
                                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                                    mBitmap1.compress(Bitmap.CompressFormat.PNG, 50, stream1);
                                    byte[] bytes1 = stream1.toByteArray();
                                    //gambar hotel2
                                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                                    mBitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream2);
                                    byte[] bytes2 = stream2.toByteArray();
                                    //gambar hotel3
                                    ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                                    mBitmap3.compress(Bitmap.CompressFormat.PNG, 50, stream3);
                                    byte[] bytes3 = stream3.toByteArray();**/
                                    intent.putExtra("image1", bytes);//put bitmap image as array of bytes
                                    //intent.putExtra("hotel1", bytes1);
                                    //intent.putExtra("hotel2", bytes2);
                                    //intent.putExtra("hotel3", bytes3);
                                    intent.putExtra("image2", bytes4);
                                    intent.putExtra("image3", bytes5);
                                    intent.putExtra("image4", bytes6);
                                    intent.putExtra("image5", bytes7);
                                    intent.putExtra("title", mTitle); // put title
                                    intent.putExtra("koordinat", mKoordinat);
                                    intent.putExtra("key", mKey);
                                    intent.putExtra("koorlat", mKoorlat);
                                    intent.putExtra("koorlong", mKoorlong);
                                    startActivity(intent); //start activity
                                    Intent intentmaps = new Intent(view.getContext(), MainActivity.class);
                                    intentmaps.putExtra("title", mTitle);
                                }
                            }

                        });

                        return viewHolder;
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        //inflate the menu; this adds items to the action bar if it present
        inflater.inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        //get input method
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        //searchView.setFocusable(false);
        //searchView.setIconified(false);
        //searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
    }
}
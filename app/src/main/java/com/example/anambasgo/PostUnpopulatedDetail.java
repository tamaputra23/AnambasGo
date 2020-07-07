package com.example.anambasgo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class PostUnpopulatedDetail extends BaseActivity implements OnMapReadyCallback {
    String direction, mTitle, key, sKoorlat, sKoorlong;
    Bitmap [] images = new Bitmap[5];
    CarouselView carouselDesc;

    Double koorlat, koorlong;
    Bitmap bitmap;
    Uri gmmIntentUri;
    Intent mapIntent;
    String goolgeMap = "com.google.android.apps.maps";
    GoogleMap mMap;
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_unpopulated_detail);
        TextView title = (TextView) findViewById(R.id.mTitleTv);
        TextView description = (TextView) findViewById(R.id.detail_description);
        TextView descriptionTv = findViewById(R.id.descriptionTv);
        TextView Navigasi = (TextView) findViewById(R.id.Navigation);
        Navigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigasi();
            }
        });
        key = getIntent().getStringExtra("key");
        direction =getIntent().getStringExtra("koordinat");
        mTitle = getIntent().getStringExtra("title");
        sKoorlat = getIntent().getStringExtra("koorlat");
        sKoorlong = getIntent().getStringExtra("koorlong");
        byte[] bytes = getIntent().getByteArrayExtra("image1");
        Bitmap image1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        byte[] bytes4 = getIntent().getByteArrayExtra("image2");
        Bitmap image2 = BitmapFactory.decodeByteArray(bytes4, 0, bytes4.length);
        byte[] bytes5 = getIntent().getByteArrayExtra("image3");
        Bitmap image3 = BitmapFactory.decodeByteArray(bytes5, 0, bytes5.length);
        byte[] bytes6 = getIntent().getByteArrayExtra("image4");
        Bitmap image4 = BitmapFactory.decodeByteArray(bytes6, 0, bytes6.length);
        byte[] bytes7 = getIntent().getByteArrayExtra("image5");
        Bitmap image5 = BitmapFactory.decodeByteArray(bytes7, 0, bytes7.length);
        koorlat = Double.parseDouble(sKoorlat);
        koorlong = Double.parseDouble(sKoorlong);

        carouselDesc = findViewById(R.id.carouselDesc);
        carouselDesc.setPageCount(images.length);
        carouselDesc.setImageListener(imageListener1);

        images[0] = image1;
        images[1] = image2;
        images[2] = image3;
        images[3] = image4;
        images[4] = image5;

        context = LocaleHelper.onAttach(PostUnpopulatedDetail.this);
        resources = context.getResources();
        descriptionTv.setText(resources.getString(R.string.description));

        title.setText(mTitle);
        if (key.equals("05")){
            description.setText(getResources().getString(R.string.pulaudurai));
        }
        else if (key.equals("06")){
            description.setText(getResources().getString(R.string.pantaipejalin));
        }
        else if (key.equals("07")){
            description.setText(getResources().getString(R.string.pulauternawan));
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    private void navigasi() {
        gmmIntentUri = Uri.parse("google.navigation:q=" + mTitle);
        // Buat Uri dari intent gmmIntentUri. Set action => ACTION_VIEW
        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Set package Google Maps untuk tujuan aplikasi yang di Intent yaitu google maps
        mapIntent.setPackage(goolgeMap);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Toast.makeText(PostUnpopulatedDetail.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void back (View view) {
        onBackPressed();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(koorlat, koorlong);
        mMap.addMarker(new MarkerOptions().position(sydney).title(mTitle));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(koorlat, koorlong), 10.0f));
    }
    ImageListener imageListener1 = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(images[position]);
        }
    };

}

package com.example.anambasgo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class PostPopulatedActivity extends BaseActivity implements OnMapReadyCallback {
    CarouselView carouselView, carouselDesc;
    String direction, mTitle, key, sKoorlat, sKoorlong;
    Double koorlat, koorlong;
    Bitmap bitmap;
    Uri gmmIntentUri;
    Intent mapIntent;
    String goolgeMap = "com.google.android.apps.maps";
    GoogleMap mMap;
    Bitmap [] images = new Bitmap[5];
    Bitmap[] sampleImages = new Bitmap[3];
    LinearLayout dropdown_detail;
    int drop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_populated);
        TextView title = (TextView) findViewById(R.id.pTitleTv);
        TextView description = (TextView) findViewById(R.id.pDetail_description);
        description.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView Navigasi = (TextView) findViewById(R.id.pNavigation);
        final ImageView dropdown = (ImageView) findViewById(R.id.pDropdown);
        TextView hotelaTv = (TextView) findViewById(R.id.hotel_detail);
        hotelaTv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        dropdown_detail = (LinearLayout) findViewById(R.id.Dropdown_detail);

        key = getIntent().getStringExtra("key");
        direction =getIntent().getStringExtra("koordinat");
        mTitle = getIntent().getStringExtra("title");
        sKoorlat = getIntent().getStringExtra("koorlat");
        sKoorlong = getIntent().getStringExtra("koorlong");
        byte[] bytes = getIntent().getByteArrayExtra("image1");
        Bitmap image1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        byte[] bytes1 = getIntent().getByteArrayExtra("hotel1");
        Bitmap bmp1 = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.length);
        byte[] bytes2 = getIntent().getByteArrayExtra("hotel2");
        Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes2, 0, bytes2.length);
        byte[] bytes3 = getIntent().getByteArrayExtra("hotel3");
        Bitmap bmp3 = BitmapFactory.decodeByteArray(bytes3, 0, bytes3.length);
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

        title.setText(mTitle);

        carouselDesc = findViewById(R.id.carouselDesc);
        carouselDesc.setPageCount(images.length);
        carouselDesc.setImageListener(imageListener1);

        images[0] = image1;
        images[1] = image2;
        images[2] = image3;
        images[3] = image4;
        images[4
                ] = image5;

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        sampleImages[0] = bmp1;
        sampleImages[1] = bmp2;
        sampleImages[2] = bmp3;

        if (key.equals("01")){
            description.setText(getResources().getString(R.string.temburun));
            hotelaTv.setText(getResources().getString(R.string.temburunhotel));
        }
        else if (key.equals("02")){
            description.setText(getResources().getString(R.string.pulaubawah));
            hotelaTv.setText(getResources().getString(R.string.pulaubawhahotel));
        }
        else if (key.equals("03")){
            description.setText(getResources().getString(R.string.batutompe));
            hotelaTv.setText(getResources().getString(R.string.batutompehotel));
        }
        else if (key.equals("04")){
            description.setText(getResources().getString(R.string.padangmelang));
            hotelaTv.setText(getResources().getString(R.string.padangmelanghotel));
        }
        dropdown_detail.setVisibility(View.GONE);
        drop = 0;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Navigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigasi();
            }
        });
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
            Toast.makeText(PostPopulatedActivity.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                    Toast.LENGTH_LONG).show();
        }
    }
    public void btnDrop (View view){
        if (drop == 1){
            dropdown_detail.setVisibility(View.GONE);
            drop = 0;
        }
        else if (drop == 0){
            dropdown_detail.setVisibility(View.VISIBLE);
            drop = 1;
        }
    }
    public void back (View view) {
        onBackPressed();
    }

    ImageListener imageListener1 = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(images[position]);
        }
    };

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(sampleImages[position]);
        }
    };
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(koorlat, koorlong);
        mMap.addMarker(new MarkerOptions().position(sydney).title(mTitle));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(koorlat, koorlong), 10.0f));
    }

}

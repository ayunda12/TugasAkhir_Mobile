package com.indahayu.aplikasiloginregister;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");



//        Picasso.get()
//                .load( "http://192.168.88.22/DyahAyuSalon/assets/images/"+image )
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder)
//                .into( (ImageView)findViewById(R.id.imageView) );
        Glide.with(this)
                .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+image )
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .into((ImageView)findViewById(R.id.imageView));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
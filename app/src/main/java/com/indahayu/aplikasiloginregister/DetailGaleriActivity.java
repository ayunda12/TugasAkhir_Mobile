package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailGaleriActivity extends AppCompatActivity {
    TextView textviewTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_galeri);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");
//        getSupportActionBar().setTitle( title );
        textviewTitle = findViewById(R.id.textView);

        textviewTitle.setText(title);
//        Picasso.get()
//                .load( "http://192.168.235.22/DyahAyuSalon/assets/images/"+image )
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
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailLayananActivity extends AppCompatActivity {
    TextView textviewTitle,textviewnama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_layanan);
        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");
        String nama = getIntent().getStringExtra("intent_name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        textviewTitle = findViewById(R.id.textViewket);
        textviewnama = findViewById(R.id.textViewnama);
        textviewTitle.setText(" '' "+title+"''");

        textviewnama.setText(nama);

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
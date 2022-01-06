package com.indahayu.aplikasiloginregister;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailTestiActivity extends AppCompatActivity {
    TextView textviewTitle,textviewtgl,textviewnama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtesti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");
        String nama = getIntent().getStringExtra("intent_name");
        String tgl = getIntent().getStringExtra("intent_tgl");
        textviewTitle = findViewById(R.id.textViewket);
        textviewtgl = findViewById(R.id.textViewtgl);
        textviewnama = findViewById(R.id.textViewnama);

        textviewTitle.setText(" '' "+title+"''");
        textviewtgl.setText(tgl);
        textviewnama.setText("- "+nama);

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
package com.indahayu.aplikasiloginregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ProfilActivity extends AppCompatActivity {
    TextView textView, txnama,txusername,txno,txalamat;
    SharedPrefManager sharedPrefManager;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView = findViewById(R.id.textViewprofil);
        txusername = findViewById(R.id.profilusername);
        txnama = findViewById(R.id.profilnama);
        txno = findViewById(R.id.profilno);
        txalamat = findViewById(R.id.profilalamat);
        imageView=findViewById(R.id.imageViewprofil);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        textView.setText(sharedPrefManager.getUser().getUsername());
        txusername.setText(sharedPrefManager.getUser().getUsername());
        txnama.setText(sharedPrefManager.getUser().getNama());
        txno.setText(sharedPrefManager.getUser().getNo_telp());
        txalamat.setText(sharedPrefManager.getUser().getAlamat());


        Glide.with(this)
                .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+sharedPrefManager.getUser().getFoto() )
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ProfilActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void PindahProfil(View view) {
        Intent intent = new Intent(ProfilActivity.this, UbahProfilActivity.class);
        startActivity(intent);
    }
    public void Pindahpwd(View view) {
        Intent intent = new Intent(ProfilActivity.this, UbahPasswordActivity.class);
        startActivity(intent);
    }
    public void PindahFoto(View view) {
        Intent intent = new Intent(ProfilActivity.this, UbahFotoActivity.class);
        startActivity(intent);
    }

}
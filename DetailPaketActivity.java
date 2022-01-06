package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DetailPaketActivity extends AppCompatActivity {
    TextView textviewTitle,detailtext,hargatext;
    Button btnPesan;
    SharedPrefManager sharedPrefManager;
    TextView etalamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_paket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String title = getIntent().getStringExtra("intent_title");
        String image = getIntent().getStringExtra("intent_image");
        String detail = getIntent().getStringExtra("intent_detail");
        String harga = getIntent().getStringExtra("intent_harga");

        etalamat = findViewById(R.id.etalamat);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        etalamat.setText(sharedPrefManager.getUser().getAlamat());

        textviewTitle = findViewById(R.id.textView);
        detailtext = findViewById(R.id.detail);
        hargatext = findViewById(R.id.harga);
        btnPesan = findViewById(R.id.btnpesan);

        textviewTitle.setText("Paket "+title);
        detailtext.setText(detail);
//        hargatext.setText(harga);
        DecimalFormat kursIdr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setMonetaryDecimalSeparator('.');
        kursIdr.setDecimalFormatSymbols(formatRp);

        hargatext.setText(kursIdr.format(Long.valueOf(harga)));

            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/" + image)
                    .placeholder(R.drawable.img_placeholder)
                    .centerCrop()
                    .into((ImageView) findViewById(R.id.imageView));


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alamat = etalamat.getText().toString();
                if (alamat.isEmpty()) {
                    Toast.makeText(DetailPaketActivity.this, "Mohon mengisi alamat lengkap anda terlebih dahulu!!", Toast.LENGTH_SHORT).show();
                    Intent intentalamat = new Intent(DetailPaketActivity.this, UbahProfilActivity.class);
                    startActivity(intentalamat);
                }else {
                    Intent intent = new Intent(DetailPaketActivity.this, PesanActivity.class);
                    String id = getIntent().getStringExtra("intent_id");
                    String title = getIntent().getStringExtra("intent_title");
                    String harga = getIntent().getStringExtra("intent_harga");

                    intent.putExtra("intent_id", id);
                    intent.putExtra("intent_title", title);
                    intent.putExtra("intent_harga", harga);
                    startActivity(intent);
                }
            }
        });
    }
//    @Override
//    public void onClick(View view) {
//
//        switch (view.getId()){
//
//            case R.id.btnpesan:
////                setupPesan();
//                break;
//
//        }
//
//    }

//    public void setupPesan(View view){
//        mainadapterpaket = new Mainadapterpaket(PaketActivity.this, results, new Mainadapterpaket.AdapterListener() {
//            @Override
//            public void onClick(Mainmodelpaket.Result result) {
//                Intent intent = new Intent(DetailPaketActivity.this, PesanActivity.class);
//                intent.putExtra("intent_title", result.getNama_paket());
//                intent.putExtra("intent_image", result.getGambar());
//                intent.putExtra("intent_detail", result.getDetail());
//                intent.putExtra("intent_harga", result.getHarga());
//                startActivity( intent );
//            }
//        });
////        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
////        recyclerView.setLayoutManager( layoutManager );
////        recyclerView.setAdapter( mainadapterpaket );
//    }
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
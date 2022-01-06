package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.indahayu.aplikasiloginregister.ModelResponse.KirimTesti;
import com.indahayu.aplikasiloginregister.ModelResponse.PesanResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePesanDP;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePesanLunas;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesananSayaActivity extends AppCompatActivity {
    private String status= "", paket="", idbooking="",harga="", tglpesan="", tglacara="",keterangan="",bukti="";
    private EditText a,b,c,d,e,f,g,h,j;
    EditText edtjml,edttesti,etiduser;
    Button btnbayar,btnbukti,btnselesai,btnbatal;
    ImageView imageView;
    SharedPrefManager sharedPrefManager;
    Mainadapterpesansaya mainadapterpesansaya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_saya);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        etiduser = findViewById(R.id.etiduser);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        etiduser.setText(String.valueOf(sharedPrefManager.getUser().getId_user()));

        status= getIntent().getStringExtra("Status Pemesanan");
        paket= getIntent().getStringExtra("Nama Paket");
        idbooking= getIntent().getStringExtra("Id booking");
        harga = getIntent().getStringExtra("Harga");
        tglacara = getIntent().getStringExtra("Tanggal Acara");
        tglpesan = getIntent().getStringExtra("Tanggal Pesan");
        keterangan = getIntent().getStringExtra("Keterangan");
        bukti = getIntent().getStringExtra("Bukti Bayar");

        DecimalFormat kursIdr = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp ");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setMonetaryDecimalSeparator('.');
        kursIdr.setDecimalFormatSymbols(formatRp);

        a = findViewById(R.id.etstatus);
        a.setText(status);
        b = findViewById(R.id.etpaket);
        b.setText(paket);
        c = findViewById(R.id.etidbooking);
        c.setText(idbooking);
        d = findViewById(R.id.etharga);
        d.setText(kursIdr.format(Long.valueOf(harga)));
        j= findViewById(R.id.hargainput);
        j.setText(harga);
        e = findViewById(R.id.ettglacara);
        e.setText(tglacara);
        f = findViewById(R.id.ettglpesan);
        f.setText(tglpesan);
        g = findViewById(R.id.etstatusbyr);
        g.setText(keterangan);
        imageView=findViewById(R.id.imagebukti);
        btnbayar = findViewById(R.id.btnbayar);
        btnbukti = findViewById(R.id.btnbukti);
        btnselesai = findViewById(R.id.btnselesai);
        btnbatal = findViewById(R.id.btnbatal);

        String idbooking = c.getText().toString();


        String statuspesan = a.getText().toString();
        if (statuspesan.equals("Menunggu Bukti Transfer")){
            btnbayar.setVisibility(View.INVISIBLE);
            btnselesai.setVisibility(View.INVISIBLE);
            btnbatal.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+bukti )
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
        }else if(statuspesan.equals("diproses")){
            btnbayar.setVisibility(View.INVISIBLE);
            btnbukti.setVisibility(View.INVISIBLE);
            btnselesai.setVisibility(View.INVISIBLE);
            btnbatal.setVisibility(View.INVISIBLE);
            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+bukti )
                    .centerCrop()
                    .placeholder(R.drawable.img_placeholder)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
        }else if(statuspesan.equals("Menunggu Pembayaran")){
            btnbayar.setVisibility(View.VISIBLE);
            btnbukti.setVisibility(View.INVISIBLE);
            btnselesai.setVisibility(View.INVISIBLE);
            btnbatal.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+bukti )
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
        }else if(statuspesan.equals("dikonfirmasi")){
            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+bukti )
                    .centerCrop()
                    .placeholder(R.drawable.img_placeholder)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView);
            btnselesai.setVisibility(View.VISIBLE);
            btnbayar.setVisibility(View.INVISIBLE);
            btnbukti.setVisibility(View.INVISIBLE);
            btnbatal.setVisibility(View.INVISIBLE);
        }else{}

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogbatal = new Dialog(DetailPesananSayaActivity.this);
                dialogbatal.setContentView(R.layout.activity_konfirmasi_batal_pesan);
                dialogbatal.show();
                ImageView keluar = dialogbatal.findViewById(R.id.keluar);
                keluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogbatal.dismiss();
                    }
                });

                Button btnno = dialogbatal.findViewById(R.id.btnno);
                btnno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogbatal.dismiss();
                    }
                });
                Button btnyes = dialogbatal.findViewById(R.id.btnyes);
                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String idbooking = c.getText().toString();
                        Call<KirimTesti> call = RetrofitClient
                                .getInstance()
                                .getApi()
                                .batalpesan(idbooking);
                        call.enqueue(new Callback<KirimTesti>() {
                            @Override
                            public void onResponse(Call<KirimTesti> call, Response<KirimTesti> response) {
                                KirimTesti kirimTesti = response.body();
                                if (response.isSuccessful()) {
                                    Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intentrek = new Intent(DetailPesananSayaActivity.this, PesananSayaActivity.class);
                                    startActivity(intentrek);
                                    dialogbatal.dismiss();
                                }
                                else {
                                    Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                            @Override
                            public void onFailure(Call<KirimTesti> call, Throwable t) {

                                Toast.makeText(DetailPesananSayaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
        btnselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idbooking = c.getText().toString();
                Call<KirimTesti> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .kirimtesti(idbooking);
                call.enqueue(new Callback<KirimTesti>() {
                    @Override
                    public void onResponse(Call<KirimTesti> call, Response<KirimTesti> response) {
                        KirimTesti kirimTesti = response.body();
                        if (response.isSuccessful()) {
                            if (kirimTesti.getError().equals("001")) {
                                Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_LONG).show();

                            }else if(kirimTesti.getError().equals("002")) {
                                String idbooking = c.getText().toString();
                                Dialog dialogtes = new Dialog(DetailPesananSayaActivity.this);
                                dialogtes.setContentView(R.layout.activity_kasih_testi);
                                dialogtes.show();
                                edttesti = dialogtes.findViewById(R.id.edttesti);
                                ImageView keluar = dialogtes.findViewById(R.id.keluar);
                                keluar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogtes.dismiss();
                                    }
                                });
                                Button btntesti = dialogtes.findViewById(R.id.btntesti);
                                btntesti.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String iduser = etiduser.getText().toString();
                                        String edttestimoni = edttesti.getText().toString();
                                        if (edttestimoni.isEmpty()) {
                                            Toast.makeText(DetailPesananSayaActivity.this, "Mohon isi testimoni", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Call<KirimTesti> call = RetrofitClient
                                                    .getInstance()
                                                    .getApi()
                                                    .kirimtestilagi(idbooking, edttestimoni, iduser);
                                            call.enqueue(new Callback<KirimTesti>() {
                                                @Override
                                                public void onResponse(Call<KirimTesti> call, Response<KirimTesti> response) {
                                                    KirimTesti kirimTesti = response.body();
                                                    if (response.isSuccessful()) {
                                                        if (kirimTesti.getError().equals("000")) {
                                                            Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                                                            dialogtes.dismiss();


                                                        } else {
                                                            Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();

                                                        }

                                                    } else {
                                                        Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }

                                                }

                                                @Override
                                                public void onFailure(Call<KirimTesti> call, Throwable t) {

                                                    Toast.makeText(DetailPesananSayaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }
                                });
                            }else {
                                Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(DetailPesananSayaActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<KirimTesti> call, Throwable t) {

                        Toast.makeText(DetailPesananSayaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnbukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogrek = new Dialog(DetailPesananSayaActivity.this);
                dialogrek.setContentView(R.layout.rekening);
                dialogrek.show();
                Button btnkonfirmasi = dialogrek.findViewById(R.id.btnkonfirmasi);
                ImageView keluar = dialogrek.findViewById(R.id.keluar);
                keluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogrek.dismiss();
                    }
                });
                btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentrek = new Intent(DetailPesananSayaActivity.this, KirimBuktiActivity.class);
                        String idbooking = getIntent().getStringExtra("Id booking");
                        intentrek.putExtra("Id booking", idbooking);
                        startActivity(intentrek);
                        dialogrek.dismiss();
                    }
                });
            }
        });
        Button btnbayar = findViewById(R.id.btnbayar);
        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogg = new Dialog(DetailPesananSayaActivity.this);
                dialogg.setContentView(R.layout.custom_design);
                ImageView keluar = dialogg.findViewById(R.id.keluar);
                keluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogg.dismiss();
                    }
                });
                String idbooking = c.getText().toString();
                String hargafix = j.getText().toString();
                Button btndp = dialogg.findViewById(R.id.btndp);
                Button btnlunas = dialogg.findViewById(R.id.btnlunas);
                btnlunas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<UpdatePesanLunas> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .pesanupdatelunas(idbooking, hargafix);
                    call.enqueue(new Callback<UpdatePesanLunas>() {
                        @Override
                        public void onResponse(Call<UpdatePesanLunas> call, Response<UpdatePesanLunas> response) {
                            UpdatePesanLunas updatePesanLunas = response.body();
                            if (response.isSuccessful()) {
                                Toast.makeText(DetailPesananSayaActivity.this, updatePesanLunas.getMessage(), Toast.LENGTH_SHORT).show();
                            Dialog dialogrek = new Dialog(DetailPesananSayaActivity.this);
                            dialogrek.setContentView(R.layout.rekening);
                            dialogrek.show();
                            dialogg.dismiss();
                            ImageView keluar = dialogrek.findViewById(R.id.keluar);
                            keluar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intentrek = new Intent(DetailPesananSayaActivity.this, PesananSayaActivity.class);
                                    startActivity(intentrek);
                                    dialogrek.dismiss();
                                }
                            });
                            Button btnkonfirmasi = dialogrek.findViewById(R.id.btnkonfirmasi);
                            btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intentrek = new Intent(DetailPesananSayaActivity.this, KirimBuktiActivity.class);
                                    String idbooking = getIntent().getStringExtra("Id booking");
                                    intentrek.putExtra("Id booking", idbooking);
                                    startActivity(intentrek);
                                    dialogrek.dismiss();
                                }
                            });
                            } else {
                                Toast.makeText(DetailPesananSayaActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<UpdatePesanLunas> call, Throwable t) {

                            Toast.makeText(DetailPesananSayaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


                btndp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(DetailPesananSayaActivity.this);
                        dialog.setContentView(R.layout.formdp);
                        ImageView keluar = dialog.findViewById(R.id.keluar);
                        keluar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        edtjml = dialog.findViewById(R.id.jml);
                        Button btntambahdp = dialog.findViewById(R.id.btntambahdp);
                        btntambahdp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String jml = edtjml.getText().toString();
                                if (jml.isEmpty()) {
                                    Toast.makeText(DetailPesananSayaActivity.this, "Mohon isi Jumlah Pembayaran", Toast.LENGTH_SHORT).show();
                                } else if (Integer.parseInt(edtjml.getText().toString()) < 500000) {
                                    Toast.makeText(DetailPesananSayaActivity.this, "Mohon masukkan minimal pembayaran Rp.500.000", Toast.LENGTH_LONG).show();

                                } else {
                                    String idbooking = c.getText().toString();
                                    String jmlh = edtjml.getText().toString();
                                    Call<UpdatePesanDP> call = RetrofitClient
                                            .getInstance()
                                            .getApi()
                                            .pesanupdatedp(idbooking, jmlh);
                                            call.enqueue(new Callback<UpdatePesanDP>() {
                                            @Override
                                            public void onResponse(Call<UpdatePesanDP> call, Response<UpdatePesanDP> response) {
                                                UpdatePesanDP updatePesanDP = response.body();
                                                if (response.isSuccessful()) {
                                                    Toast.makeText(DetailPesananSayaActivity.this, updatePesanDP.getMessage(), Toast.LENGTH_SHORT).show();
//
                                                    Dialog dialogrek = new Dialog(DetailPesananSayaActivity.this);
                                                    dialogrek.setContentView(R.layout.rekening);
                                                    dialogrek.show();
                                                    dialog.dismiss();
                                                    ImageView keluar = dialogrek.findViewById(R.id.keluar);
                                                    keluar.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent intentrek = new Intent(DetailPesananSayaActivity.this, PesananSayaActivity.class);
                                                            startActivity(intentrek);
                                                            dialogrek.dismiss();
                                                        }
                                                    });
                                                    Button btnkonfirmasi = dialogrek.findViewById(R.id.btnkonfirmasi);
                                                    btnkonfirmasi.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Intent intentrek = new Intent(DetailPesananSayaActivity.this, KirimBuktiActivity.class);
                                                            String idbooking = getIntent().getStringExtra("Id booking");
                                                            intentrek.putExtra("Id booking", idbooking);
                                                            startActivity(intentrek);
                                                            dialogrek.dismiss();
                                                        }
                                                    });
                                                } else {
                                                    Toast.makeText(DetailPesananSayaActivity.this, "failed", Toast.LENGTH_SHORT).show();

                                                }

                                            }
//
                                            @Override
                                            public void onFailure(Call<UpdatePesanDP> call, Throwable t) {

                                                Toast.makeText(DetailPesananSayaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                }
                            }
                        });
                        dialog.show();
                        dialogg.dismiss();
                    }
                });

                dialogg.show();
            }

        });


    }

    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(DetailPesananSayaActivity.this,PesananSayaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}
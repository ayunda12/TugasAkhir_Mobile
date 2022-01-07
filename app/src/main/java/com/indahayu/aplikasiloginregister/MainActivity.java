package com.indahayu.aplikasiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.indahayu.aplikasiloginregister.ModelResponse.KirimTesti;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    TextView etUsername,etalamat;
    SharedPrefManager sharedPrefManager;

    Button btnpaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPrefManager = new SharedPrefManager(MainActivity.this);
        if(!sharedPrefManager.isLoggedIn()){
            moveToLogin();
        }

        etUsername = findViewById(R.id.tvnama);
        etalamat = findViewById(R.id.tvalamat);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        etUsername.setText(sharedPrefManager.getUser().getUsername());
        etalamat.setText(sharedPrefManager.getUser().getAlamat());
        final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                Call<KirimTesti> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .hapusauto();
                call.enqueue(new Callback<KirimTesti>() {
                    @Override
                    public void onResponse(Call<KirimTesti> call, Response<KirimTesti> response) {
                        KirimTesti kirimTesti = response.body();
                        if (response.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        else {
                            //Toast.makeText(MainActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<KirimTesti> call, Throwable t) {

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(refresh, 5000);
        Runnable refreshh = new Runnable() {
            @Override
            public void run() {
                Call<KirimTesti> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .hapusautonext();
                call.enqueue(new Callback<KirimTesti>() {
                    @Override
                    public void onResponse(Call<KirimTesti> call, Response<KirimTesti> response) {
                        KirimTesti kirimTesti = response.body();
                        if (response.isSuccessful()) {
                            //Toast.makeText(MainActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        else {
                            //Toast.makeText(MainActivity.this, kirimTesti.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<KirimTesti> call, Throwable t) {

                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(refreshh, 5000);
        btnpaket = findViewById(R.id.btnpilihpaket);
        btnpaket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialogpaket = new Dialog(MainActivity.this);
                dialogpaket.setContentView(R.layout.activity_pilih_paket);
                dialogpaket.show();
                ImageView keluar = dialogpaket.findViewById(R.id.keluar);
                keluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogpaket.dismiss();
                    }
                });

                Button btnno = dialogpaket.findViewById(R.id.btnno);
                btnno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogpaket.dismiss();
                    }
                });
                Button btnyes = dialogpaket.findViewById(R.id.btnyes);
                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String alamat = etalamat.getText().toString();
                        if (alamat.isEmpty()){
                            Toast.makeText(MainActivity.this, "Mohon mengisi alamat lengkap anda terlebih dahulu!!", Toast.LENGTH_SHORT).show();
                            Intent intentalamat = new Intent(MainActivity.this, UbahProfilActivity.class);
                            startActivity(intentalamat);
                            dialogpaket.dismiss();
                        }else {
                            Intent intentrek = new Intent(MainActivity.this, PaketActivity.class);
                            startActivity(intentrek);
                            dialogpaket.dismiss();
                        }
                    }
                });
            }
        });


    }

    private void moveToLogin() {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                Dialog dialogbatal = new Dialog(MainActivity.this);
                dialogbatal.setContentView(R.layout.activity_konfirmasilogout);
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
                        sharedPrefManager.logout();
                        moveToLogin();
                        Toast.makeText(MainActivity.this, "Anda berhasil keluar", Toast.LENGTH_SHORT).show();

                    }
                });
                 break;
            case R.id.profil:
                Intent intent = new Intent(MainActivity.this,ProfilActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            case R.id.pesanan:
                Intent intentt = new Intent(MainActivity.this,PesananSayaActivity.class);
                intentt.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intentt);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void PindahTentang(View view) {
        Intent intent = new Intent(MainActivity.this, TentangActivity.class);
        startActivity(intent);
    }
    public void PindahLayanan(View view) {
        Intent intent = new Intent(MainActivity.this, LayananActivity.class);
        startActivity(intent);
    }
    public void PindahPesan(View view) {
        Intent i = new Intent(MainActivity.this,PesanActivity.class);
        startActivity(i);
    }

    public void PindahGaleri(View view) {
        Intent intent = new Intent(MainActivity.this, GaleriActivity.class);
        startActivity(intent);
    }
    public void PindahTesti(View view) {
        Intent intent = new Intent(MainActivity.this, TestimoniActivity.class);
        startActivity(intent);
    }
    public void PindahKontak(View view) {
        Intent intent = new Intent(MainActivity.this, KontakActivity.class);
        startActivity(intent);
    }
    public void PindahPaket(View view) {
        Intent intent = new Intent(MainActivity.this, PaketActivity.class);
        startActivity(intent);
    }

}
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.api.ApiInterface;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PesananSayaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Mainadapterpesansaya mainadapterpesansaya;
    SharedPrefManager sharedPrefManager;
    TextView txtid;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_saya);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        refreshLayout = findViewById(R.id.swipe_to_refresh_layout);
        txtid = (TextView) findViewById(R.id.txtid);
        sharedPrefManager=new SharedPrefManager(getApplicationContext());

        txtid.setText(String.valueOf(sharedPrefManager.getUser().getId_user()));
        txtid.setVisibility(View.INVISIBLE);
        String iduser = txtid.getText().toString();
        RetrofitClient.getInstance().getApi().getDataPesan(iduser)
                .enqueue(new Callback<List<Mainmodelpesansaya>>() {
            @Override
            public void onResponse(Call<List<Mainmodelpesansaya>> call, Response<List<Mainmodelpesansaya>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Mainmodelpesansaya>> call, Throwable t) {
                Toast.makeText(PesananSayaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        refreshLayout.setColorSchemeResources(
                R.color.colorAccent, R.color.colorAccent,
                android.R.color.holo_orange_dark, android.R.color.holo_red_dark);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RetrofitClient.getInstance().getApi().getDataPesan(iduser)
                                .enqueue(new Callback<List<Mainmodelpesansaya>>() {
                                    @Override
                                    public void onResponse(Call<List<Mainmodelpesansaya>> call, Response<List<Mainmodelpesansaya>> response) {
                                        generateDataList(response.body());
                                    }

                                    @Override
                                    public void onFailure(Call<List<Mainmodelpesansaya>> call, Throwable t) {
                                        Toast.makeText(PesananSayaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                                    }
                                });
                        refreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });
    }

    public void generateDataList(List<Mainmodelpesansaya> aa){
        recyclerView = findViewById(R.id.recyclerView);
        mainadapterpesansaya = new Mainadapterpesansaya(this, aa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PesananSayaActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainadapterpesansaya);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(PesananSayaActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



}
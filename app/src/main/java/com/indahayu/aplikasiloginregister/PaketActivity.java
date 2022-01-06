package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaketActivity extends AppCompatActivity{
    private final String TAG = "PaketActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Mainadapterpaket mainadapterpaket;
    private ArrayList<Mainmodelpaket.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupView();
        setupRecyclerView();
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

    @Override
    protected void onStart() {
        super.onStart();
        showLoading(false);
        getDataFromApi();
    }

    private void setupView () {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setupRecyclerView (){
        mainadapterpaket = new Mainadapterpaket(this, results, new Mainadapterpaket.AdapterListener() {
            @Override
            public void onClick(Mainmodelpaket.Result result) {
                Intent intent = new Intent(PaketActivity.this, DetailPaketActivity.class);
                intent.putExtra("intent_id", result.getId_paket());
                intent.putExtra("intent_title", result.getNama_paket());
                intent.putExtra("intent_image", result.getGambar());
                intent.putExtra("intent_detail", result.getDetail());
                intent.putExtra("intent_harga", result.getHarga());
                startActivity( intent );
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( mainadapterpaket );
    }


    private void getDataFromApi() {
        showLoading( true );
        RetrofitClient.getInstance().getApi().getDatapaket()
                .enqueue(new Callback<Mainmodelpaket>() {
                    @Override
                    public void onResponse(Call<Mainmodelpaket> call, Response<Mainmodelpaket> response) {
                        showLoading( false );
                        Log.d( TAG, "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            List<Mainmodelpaket.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainadapterpaket.setData( results );
                        }
                    }
                    @Override
                    public void onFailure(Call<Mainmodelpaket> call, Throwable t) {
                        showLoading( false );
                        Log.d( TAG, t.toString());
                    }
                });
    }

    private void showLoading(Boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaleriActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdaptergaleri mainAdaptergaleri;
    private ArrayList<Mainmodelgaleri.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);
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
        mainAdaptergaleri = new MainAdaptergaleri(this, results, new MainAdaptergaleri.AdapterListener() {
            @Override
            public void onClick(Mainmodelgaleri.Result result) {
                Intent intent = new Intent(GaleriActivity.this, DetailGaleriActivity.class);
                intent.putExtra("intent_title", result.getKeterangan());
                intent.putExtra("intent_image", result.getFoto());
                startActivity( intent );
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( mainAdaptergaleri );
    }

    private void getDataFromApi() {
        showLoading( true );
        RetrofitClient.getInstance().getApi().getDataGaleri()
                .enqueue(new Callback<Mainmodelgaleri>() {
                    @Override
                    public void onResponse(Call<Mainmodelgaleri> call, Response<Mainmodelgaleri> response) {
                        showLoading( false );
                        Log.d( TAG, "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            List<Mainmodelgaleri.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdaptergaleri.setData( results );
                        }
                    }
                    @Override
                    public void onFailure(Call<Mainmodelgaleri> call, Throwable t) {
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
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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


public class LayananActivity extends AppCompatActivity {

    private final String TAG = "LayananActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;
    private ArrayList<MainModel.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan);
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
        mainAdapter = new MainAdapter(this, results, new MainAdapter.AdapterListener() {
            @Override
            public void onClick(MainModel.Result result) {
                Intent intent = new Intent(LayananActivity.this, DetailLayananActivity.class);
                intent.putExtra("intent_title", result.getNama());
                intent.putExtra("intent_name", result.getKeterangan());
                intent.putExtra("intent_image", result.getGambar());
                startActivity( intent );
            }
        });
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager( mGridLayoutManager );
        recyclerView.setAdapter( mainAdapter );
    }

    private void getDataFromApi() {
        showLoading( true );
        RetrofitClient.getInstance().getApi().getData()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        showLoading( false );
                        Log.d( TAG, "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            List<MainModel.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdapter.setData( results );
                        }
                    }
                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
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
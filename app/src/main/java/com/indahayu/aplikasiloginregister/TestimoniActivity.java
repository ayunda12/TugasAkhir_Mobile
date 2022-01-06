
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

public class TestimoniActivity extends AppCompatActivity {
    private final String TAG = "TestimoniActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Mainadaptertesti mainadaptertesti;
    private ArrayList<Mainmodeltesti.Result> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimoni);
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
        mainadaptertesti = new Mainadaptertesti(this, results, new Mainadaptertesti.AdapterListener() {
            @Override
            public void onClick(Mainmodeltesti.Result result) {
                Intent intent = new Intent(TestimoniActivity.this, DetailTestiActivity.class);
                intent.putExtra("intent_title", result.getKeterangan());
                intent.putExtra("intent_tgl", result.getTgl_upload());
                intent.putExtra("intent_name", result.getNama());
                intent.putExtra("intent_image", result.getFoto());
                startActivity( intent );
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( mainadaptertesti );
    }

    private void getDataFromApi() {
        showLoading( true );
        RetrofitClient.getInstance().getApi().getDataTesti()
                .enqueue(new Callback<Mainmodeltesti>() {
                    @Override
                    public void onResponse(Call<Mainmodeltesti> call, Response<Mainmodeltesti> response) {
                        showLoading( false );
                        Log.d( TAG, "onResponse: " + response.toString());
                        if (response.isSuccessful()) {
                            List<Mainmodeltesti.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainadaptertesti.setData( results );
                        }
                    }
                    @Override
                    public void onFailure(Call<Mainmodeltesti> call, Throwable t) {
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
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.ModelResponse.PesanResponse;
import com.indahayu.aplikasiloginregister.ModelResponse.RegisterResponse;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanActivity extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private EditText btDatePicker;
    SharedPrefManager sharedPrefManager;
    Button btnbooking,btntambahdp;
    TextView textviewTitle,textviewid,textviewnama,txtid,txtharga;
    private Button ShowDialog;
    int userId;
    //tanggal

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String id = getIntent().getStringExtra("intent_id");
        String title = getIntent().getStringExtra("intent_title");
        String harga = getIntent().getStringExtra("intent_harga");
        btnbooking = findViewById(R.id.btnbooking);
        btnbooking.setOnClickListener(this);

        textviewTitle = findViewById(R.id.edittextpaket);
        textviewid = findViewById(R.id.edittextid);
        textviewid.setText(id);
        textviewTitle.setText(title);
        textviewid.setVisibility(View.INVISIBLE);

        textviewnama = findViewById(R.id.edittextnama);
        txtid = (TextView) findViewById(R.id.edittextuserid);
        txtid.setVisibility(View.INVISIBLE);

        txtharga = findViewById(R.id.txtharga);
        txtharga.setText(harga);
        txtharga.setVisibility(View.INVISIBLE);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        textviewnama.setText(sharedPrefManager.getUser().getNama());
        txtid.setText(String.valueOf(sharedPrefManager.getUser().getId_user()));

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.tv_dateresult);
        btDatePicker = (EditText) findViewById(R.id.tv_dateresult);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnbooking:
                Pesanpaket();
                break;

        }
    }

    private void Pesanpaket() {

        String iduser = txtid.getText().toString();
        String idpaket = textviewid.getText().toString();
        String tgl = tvDateResult.getText().toString();
        String harga = txtharga.getText().toString();

        if (tgl.isEmpty()) {
            Toast.makeText(PesanActivity.this, "Mohon isi tanggal", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<PesanResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .pesan(iduser,idpaket,tgl,harga);

        call.enqueue(new Callback<PesanResponse>() {
            @Override
            public void onResponse(Call<PesanResponse> call, Response<PesanResponse> response) {

                PesanResponse pesanResponse = response.body();
                if (response.isSuccessful()) {
                    if (pesanResponse.getError().equals("000")) {
                        Toast.makeText(PesanActivity.this, pesanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(PesanActivity.this, PesananSayaActivity.class);
                         startActivity(intent);

                    }else{
                        Toast.makeText(PesanActivity.this, pesanResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(PesanActivity.this, pesanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PesanResponse> call, Throwable t) {

                Toast.makeText(PesanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
        super.onBackPressed();
    }


}
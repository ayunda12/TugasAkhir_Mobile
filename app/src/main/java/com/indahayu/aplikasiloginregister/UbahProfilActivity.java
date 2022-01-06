package com.indahayu.aplikasiloginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.ModelResponse.LoginResponse;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  UbahProfilActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etuserName,etnama,currentPass, newPass, etno,etalamat;
    ImageView fotoo;
    Button updateUserAccountBtn,updateuserPasswordBtn;
    SharedPrefManager sharedPrefManager;
    int userId;
    String userNameId,foto,Nama,no_telp,alamat;

    int IMG_REQUEST = 21;
    Bitmap bitmap;
    ImageView imageView;
    Button btnSelectImage, btnUploadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        etnama=findViewById(R.id.nama);
        etuserName=findViewById(R.id.userName);
        etno=findViewById(R.id.notelp);
        etalamat=findViewById(R.id.alamat);
        updateUserAccountBtn=findViewById(R.id.btnUpdateAccount);

        sharedPrefManager = new SharedPrefManager(UbahProfilActivity.this);
        userId = sharedPrefManager.getUser().getId_user();
        Nama = sharedPrefManager.getUser().getNama();
        userNameId = sharedPrefManager.getUser().getUsername();
        foto = sharedPrefManager.getUser().getFoto();
        no_telp = sharedPrefManager.getUser().getNo_telp();
        alamat = sharedPrefManager.getUser().getAlamat();


        etnama.setText(Nama);
        etuserName.setText(userNameId);
        etno.setText(no_telp);
        etalamat.setText(alamat);


        updateUserAccountBtn.setOnClickListener(this);

        etnama.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z ]+")){
                            return cs;
                        }
                        return "";
                    }
                }});

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(UbahProfilActivity.this,ProfilActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnUpdateAccount:
                updateUserAccount();
                break;


        }

    }

    private void updateUserAccount() {

        String username=etuserName.getText().toString().trim();
        String nama = etnama.getText().toString().trim();
        String notelp = etno.getText().toString().trim();
        String alamat = etalamat.getText().toString().trim();


        if(username.isEmpty()){
            etuserName.setError("Masukkan username anda");
            etuserName.requestFocus();
            return;
        }
        if(nama.isEmpty()){
            etnama.setError("Masukkan nama lengkap anda");
            etnama.requestFocus();
            return;
        }

        if(notelp.isEmpty()){
            etno.setError("Masukkan No HP/ Wa anda");
            etno.requestFocus();
            return;
        }
        if(alamat.isEmpty()){
            etalamat.setError("Masukkan alamat lengkap anda");
            etalamat.requestFocus();
            return;
        }

        Call<LoginResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserAccount(userId,username,nama,notelp,alamat);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                LoginResponse updateReponse=response.body();
                if(response.isSuccessful()){

                    if(updateReponse.getError().equals("200")){

                        sharedPrefManager.saveUser(updateReponse.getUser());
                        Intent intent=new Intent(UbahProfilActivity.this,ProfilActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(UbahProfilActivity.this, updateReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UbahProfilActivity.this, updateReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    Toast.makeText(UbahProfilActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(UbahProfilActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
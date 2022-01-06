package com.indahayu.aplikasiloginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.indahayu.aplikasiloginregister.ModelResponse.ResponsePOJO;
import com.indahayu.aplikasiloginregister.ModelResponse.UpdateBukti;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KirimBuktiActivity extends AppCompatActivity {
    TextView textviewid;
    String foto;

    int IMG_REQUEST = 21;
    Bitmap bitmap;
    ImageView imageView;
    Button btnSelectImage, btnUploadImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_bukti);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String idbooking = getIntent().getStringExtra("Id booking");
        textviewid = findViewById(R.id.txtbooking);
        textviewid.setText(idbooking);
        textviewid.setVisibility(View.INVISIBLE);

        imageView = findViewById(R.id.imageViewEdit);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnUploadImage.setVisibility(View.INVISIBLE);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, IMG_REQUEST);
            }
        });
        Glide.with(this)
                .load(R.drawable.img_placeholder )
                .placeholder(R.drawable.img_placeholder)
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into((ImageView)findViewById(R.id.imageViewEdit) );
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                btnUploadImage.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {
        String idbooking = textviewid.getText().toString();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage =  Base64.encodeToString(imageInByte,Base64.DEFAULT);
        if (encodedImage.isEmpty()){
            Toast.makeText(KirimBuktiActivity.this, "Mohon isi gambar", Toast.LENGTH_SHORT).show();

        }

        Call<UpdateBukti> call = RetrofitClient
                .getInstance()
                .getApi()
                .pesanupdatebukti(idbooking,encodedImage);
        call.enqueue(new Callback<UpdateBukti>() {
            @Override
            public void onResponse(Call<UpdateBukti> call, Response<UpdateBukti> response) {

                UpdateBukti updateBukti=response.body();
                if(response.isSuccessful()){

                    if (updateBukti.getError().equals("400")) {
                        Toast.makeText(KirimBuktiActivity.this, updateBukti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KirimBuktiActivity.this, PesananSayaActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(KirimBuktiActivity.this, updateBukti.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(KirimBuktiActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<UpdateBukti> call, Throwable t) {
                Toast.makeText(KirimBuktiActivity.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(KirimBuktiActivity.this,PesananSayaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
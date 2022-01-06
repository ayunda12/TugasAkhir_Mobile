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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.indahayu.aplikasiloginregister.ModelResponse.ResponsePOJO;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahFotoActivity extends AppCompatActivity{

    SharedPrefManager sharedPrefManager;
    int userId;
    String foto;

    int IMG_REQUEST = 21;
    Bitmap bitmap;
    ImageView imageView;
    Button btnSelectImage, btnUploadImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_foto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPrefManager = new SharedPrefManager(UbahFotoActivity.this);
        foto = sharedPrefManager.getUser().getFoto();
        userId = sharedPrefManager.getUser().getId_user();


//        Glide.with(this)
//                .load("https://ws-tif.com/dyah-ayu-salon/assets/images/"+foto )
//                .placeholder(R.drawable.img_placeholder)
//                .centerCrop()
//                .fitCenter()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
//                .into((ImageView)findViewById(R.id.imageViewEdit) );

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
        if (foto.toString().isEmpty()) {
            Glide.with(this)
                    .load(R.drawable.img_placeholder)
                    .placeholder(R.drawable.img_placeholder)
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into((ImageView) findViewById(R.id.imageViewEdit));
        }else{
            Glide.with(this)
                    .load("https://ws-tif.com/dyah-ayu-salon/assets/images/" + foto)
                    .placeholder(R.drawable.img_placeholder)
                    .centerCrop()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into((ImageView) findViewById(R.id.imageViewEdit));
        }
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
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
        Intent intent=new Intent(UbahFotoActivity.this,ProfilActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage =  Base64.encodeToString(imageInByte,Base64.DEFAULT);


        Call<ResponsePOJO> call = RetrofitClient
                .getInstance()
                .getApi()
                .uploadImage(userId,encodedImage);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {

                ResponsePOJO upReponse=response.body();
                if(response.isSuccessful()){

                    if(upReponse.getError().equals("200")){

                        sharedPrefManager.saveUser(upReponse.getUser());
                        Intent intent=new Intent(UbahFotoActivity.this,ProfilActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(UbahFotoActivity.this, upReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UbahFotoActivity.this, upReponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(UbahFotoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(UbahFotoActivity.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.ModelResponse.UpdatePassResponse;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText currentPass, newPass;
    Button updateuserPasswordBtn;
    SharedPrefManager sharedPrefManager;
    String userNameId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());
        currentPass = findViewById(R.id.currentPass);

        newPass = findViewById(R.id.newPassword);
        updateuserPasswordBtn= findViewById(R.id.btnUpdatePassword);

        sharedPrefManager = new SharedPrefManager(UbahPasswordActivity.this);
        userNameId = sharedPrefManager.getUser().getUsername();
        updateuserPasswordBtn.setOnClickListener(this);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(UbahPasswordActivity.this,ProfilActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnUpdatePassword:
                updatePassword();
                break;
        }

    }
    private void updatePassword() {

        String userCurrentPassword=currentPass.getText().toString().trim();
        String userNewPassword=newPass.getText().toString().trim();

        if(userCurrentPassword.isEmpty()){
            currentPass.setError("Masukkan Password Lama");
            currentPass.requestFocus();
            return;
        }

        if(userCurrentPassword.length()<5){
            currentPass.setError("Password tidak boleh kurang dari 5 karakter");
            currentPass.requestFocus();
            return;
        }


        if(userNewPassword.isEmpty()){
            newPass.setError("Masukkan Password Baru");
            newPass.requestFocus();
            return;
        }

        if(userNewPassword.length()<5){
            newPass.setError("Password tidak boleh kurang dari 5 karakter");
            newPass.requestFocus();
            return;
        }



        Call<UpdatePassResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .updateUserPass(userNameId,userCurrentPassword,userNewPassword);

        call.enqueue(new Callback<UpdatePassResponse>() {
            @Override
            public void onResponse(Call<UpdatePassResponse> call, Response<UpdatePassResponse> response) {

                UpdatePassResponse passwordResponse=response.body();


                if(response.isSuccessful()){


                    if(passwordResponse.getError().equals("200")){
                        Intent intent=new Intent(UbahPasswordActivity.this,ProfilActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(UbahPasswordActivity.this, passwordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(UbahPasswordActivity.this, passwordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(UbahPasswordActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdatePassResponse> call, Throwable t) {

                Toast.makeText(UbahPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
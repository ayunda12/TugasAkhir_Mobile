package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.ModelResponse.LoginResponse;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        tvRegister=findViewById(R.id.registerlink);

        tvRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        sharedPrefManager=new SharedPrefManager(getApplicationContext());
//        btnLogin = findViewById(R.id.btnLogin);
//        btnLogin.setOnClickListener(this);
//
//        tvRegister = findViewById(R.id.registerlink);
//        tvRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.registerlink:
                switchOnRegister();
                break;

        }

    }

    private void userLogin() {

        String userName = etUsername.getText().toString();
        String userPassword = etPassword.getText().toString();

        if(userName.isEmpty()){
            etUsername.requestFocus();
            etUsername.setError("Masukkan username anda");
            return;
        }

        if(userPassword.isEmpty()){
            etPassword.requestFocus();
            etPassword.setError("Masukkan password anda");
            return;
        }
        if(userPassword.length()<5){
            etPassword.requestFocus();
            etPassword.setError("Password tidak boleh kurang dari 5 karakter");
            return;
        }

        Call<LoginResponse> call= RetrofitClient.getInstance().getApi().login(userName,userPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse=response.body();

                if(response.isSuccessful()) {

                    if (loginResponse.getError().equals("200")) {

                        sharedPrefManager.saveUser(loginResponse.getUser());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    } else if(loginResponse.getError().equals("300")) {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        etPassword.requestFocus();
                    }else if(loginResponse.getError().equals("400")) {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        etUsername.requestFocus();
                    }else{
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void switchOnRegister() {
        Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPrefManager.isLoggedIn()){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}
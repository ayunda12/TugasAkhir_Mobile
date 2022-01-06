package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indahayu.aplikasiloginregister.ModelResponse.RegisterResponse;
import com.indahayu.aplikasiloginregister.api.ApiInterface;
import com.indahayu.aplikasiloginregister.api.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername, etPassword, etName,etno;
    Button btnRegister;
    TextView tvLogin;
    String Username, Password, Nama,No;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        etName = findViewById(R.id.etRegisterName);
        etno = findViewById(R.id.etnotelp);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        etName.setFilters(new InputFilter[] {
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

        tvLogin = findViewById(R.id.loginlink);
        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                registerUser();
                break;
            case R.id.loginlink:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void registerUser() {

        String userName = etUsername.getText().toString();
        String Nama = etName.getText().toString();
        String userPassword = etPassword.getText().toString();
        String No = etno.getText().toString();


        if (userName.isEmpty()) {
            etUsername.requestFocus();
            etUsername.setError("Masukkan username anda");
            return;
        }
        if (Nama.isEmpty()) {
            etName.requestFocus();
            etName.setError("Masukkan nama anda");
            return;
        }

        if (userPassword.isEmpty()) {
            etPassword.requestFocus();
            etPassword.setError("Masukkan password anda");
            return;
        }
        if (userPassword.length() < 5) {
            etPassword.requestFocus();
            etPassword.setError("Password tidak boleh kurang dari 5 karakter");
            return;
        }
        if (No.isEmpty()) {
            etno.requestFocus();
            etno.setError("Masukkan No Hp/Wa anda");
            return;
        }


            Call<RegisterResponse> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .register(userName, Nama, userPassword, No);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                    RegisterResponse registerResponse = response.body();
                    if (response.isSuccessful()) {
                        if (registerResponse.getError().equals("002")) {
                            Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else if (registerResponse.getError().equals("000")) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }


    }
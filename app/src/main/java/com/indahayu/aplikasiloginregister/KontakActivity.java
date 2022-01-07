package com.indahayu.aplikasiloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class KontakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView linkTextView2 = findViewById(R.id.waa);
        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView2.setLinkTextColor(Color.BLACK);

        TextView fb = findViewById(R.id.fb);
        fb.setMovementMethod(LinkMovementMethod.getInstance());
        fb.setLinkTextColor(Color.BLACK);

        TextView ig = findViewById(R.id.ig);
        ig.setMovementMethod(LinkMovementMethod.getInstance());
        ig.setLinkTextColor(Color.BLACK);

        TextView alamat = findViewById(R.id.alamat);
        alamat.setMovementMethod(LinkMovementMethod.getInstance());
        alamat.setLinkTextColor(Color.BLACK);


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
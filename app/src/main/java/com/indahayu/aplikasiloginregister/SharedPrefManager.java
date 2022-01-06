package com.indahayu.aplikasiloginregister;

import android.content.Context;
import android.content.SharedPreferences;

import com.indahayu.aplikasiloginregister.ModelResponse.User;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME="dyahayu";
    private static SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_NAMA = "keyname";
    private static final String KEY_ALAMAT = "keyalamat";
    private static final String KEY_ID = "keyid";
    private static final String KEY_FOTO = "keyfoto";
    private static final String KEY_NO= "keyno";

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(User user){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId_user());
        editor.putString(KEY_NAMA,user.getNama());
        editor.putString(KEY_USERNAME,user.getUsername());
        editor.putString(KEY_FOTO,user.getFoto());
        editor.putString(KEY_NO,user.getNo_telp());
        editor.putString(KEY_ALAMAT,user.getAlamat());
        editor.putBoolean("logged",true);
        editor.apply();


    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }


    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID,-1),
                sharedPreferences.getString(KEY_NAMA,null),
                sharedPreferences.getString(KEY_USERNAME,null),
                sharedPreferences.getString(KEY_FOTO,null),
                sharedPreferences.getString(KEY_NO,null),
                sharedPreferences.getString(KEY_ALAMAT,null)
        );
    }


    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}
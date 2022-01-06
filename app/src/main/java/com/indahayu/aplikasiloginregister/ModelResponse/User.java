package com.indahayu.aplikasiloginregister.ModelResponse;

public class User {
    int id_user;
    String nama,username,foto,no_telp,alamat;



    public User(int id_user, String nama, String username,  String foto, String no_telp, String alamat) {
        this.id_user = id_user;
        this.nama = nama;
        this.username = username;
        this.foto = foto;
        this.no_telp = no_telp;
        this.alamat = alamat;


    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}



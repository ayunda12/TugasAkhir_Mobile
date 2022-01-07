package com.indahayu.aplikasiloginregister;

import java.util.List;

public class MainModel {

    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        private int id;
        private String nama;
        private String gambar;
        private String keterangan;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", nama='" + nama + '\'' +
                    ", gambar='" + gambar + '\'' +
                    ", keterangan='" + keterangan + '\'' +
                    '}';
        }
    }
}

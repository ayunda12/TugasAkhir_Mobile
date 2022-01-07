package com.indahayu.aplikasiloginregister;

import java.util.List;


public class Mainmodelpaket {
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {
        private String id_paket;
        private String nama_paket,gambar,detail,harga;

        public String getId_paket() {
            return id_paket;
        }

        public void setId_paket(String id_paket) {
            this.id_paket = id_paket;
        }

        public String getNama_paket() {
            return nama_paket;
        }

        public void setNama_paket(String nama_paket) {
            this.nama_paket = nama_paket;
        }

        public String getGambar() {
            return gambar;
        }

        public void setGambar(String gambar) {
            this.gambar = gambar;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }
        @Override
        public String toString() {
            return "Result{" +
                    "id_paket='" + id_paket +'\''+
                    ", nama_paket='" + nama_paket + '\'' +
                    ", gambar='" + gambar + '\'' +
                    ", detail='" + detail + '\'' +
                    ", harga='" + harga + '\'' +
                    '}';
        }
    }
}

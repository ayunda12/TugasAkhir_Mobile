package com.indahayu.aplikasiloginregister;

import java.util.List;

public class Mainmodeltesti {
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        private int id_testimoni;
        private String keterangan;
        private String tgl_upload;
        private String foto;
        private String nama;

        public int getId_testimoni() {
            return id_testimoni;
        }

        public void setId_testimoni(int id_testimoni) {
            this.id_testimoni = id_testimoni;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getTgl_upload() {
            return tgl_upload;
        }

        public void setTgl_upload(String tgl_upload) {
            this.tgl_upload = tgl_upload;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id_testimoni=" + id_testimoni +
                    ", keterangan='" + keterangan + '\'' +
                    ", foto='" + foto + '\'' +
                    ", nama='" + nama + '\'' +
                    ", tgl_upload='" + tgl_upload + '\'' +
                    '}';
        }
    }
}

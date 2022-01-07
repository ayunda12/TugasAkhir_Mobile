package com.indahayu.aplikasiloginregister;

import com.google.gson.annotations.SerializedName;

public class Mainmodelpesansaya {
    @SerializedName("id_user")
    private String id_user;

    @SerializedName("id_booking")
    private String id_booking;

    @SerializedName("status")
    private String status;

    @SerializedName("id_paket")
    private String id_paket;

    @SerializedName("nama_paket")
    private String nama_paket;

    @SerializedName("tgl_booking")
    private String tgl_booking;

    @SerializedName("tgl_acara")
    private String tgl_acara;


    @SerializedName("harga")
    private String harga;

    @SerializedName("keterangan_bayar")
    private String keterangan_bayar;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    public Mainmodelpesansaya(String id_user, String id_booking, String status, String id_paket, String nama_paket, String tgl_booking, String tgl_acara, String harga,String keterangan_bayar,String bukti_bayar) {
        this.id_user = id_user;
        this.id_booking = id_booking;
        this.status = status;
        this.id_paket = id_paket;
        this.nama_paket = nama_paket;
        this.tgl_booking = tgl_booking;
        this.tgl_acara = tgl_acara;
        this.harga = harga;
        this.keterangan_bayar = keterangan_bayar;
        this.bukti_bayar = bukti_bayar;
    }


    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_booking() {
        return id_booking;
    }

    public void setId_booking(String id_booking) {
        this.id_booking = id_booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getTgl_booking() {
        return tgl_booking;
    }

    public void setTgl_booking(String tgl_booking) {
        this.tgl_booking = tgl_booking;
    }

    public String getTgl_acara() {
        return tgl_acara;
    }

    public void setTgl_acara(String tgl_acara) {
        this.tgl_acara = tgl_acara;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeterangan_bayar() {
        return keterangan_bayar;
    }

    public void setKeterangan_bayar(String keterangan_bayar) {
        this.keterangan_bayar = keterangan_bayar;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public void setBukti_bayar(String bukti_bayar) {
        this.bukti_bayar = bukti_bayar;
    }
    //    public class Result {
//        private int id_booking;
//        private String status;
//        private String id_user;
//
//        public Mainmodelpesansaya(int id_booking,String id_user,String status) {
//            this.id_user = id_user;
//        }
//
//        public String getId_user() {
//            return id_user;
//        }
//
//        public void setId_user(String id_user) {
//            this.id_user = id_user;
//        }
//        public Result(String id_user,int id_booking, String status){
//            this.id_user=id_user;
//            this.id_booking=id_booking;
//            this.status=status;
//        }
//
//        public int getId_booking() {
//            return id_booking;
//        }
//
//        public void setId_booking(int id_booking) {
//            this.id_booking = id_booking;
//        }
//
//        public String getStatus() {
//            return status;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//
//        @Override
//        public String toString() {
//            return "Result{" +
//                    "id_booking=" + id_booking +
//                    ", status='" + status + '\'' +
//                    '}';
//        }
//    }
}

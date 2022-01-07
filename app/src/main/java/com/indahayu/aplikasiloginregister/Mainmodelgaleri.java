package com.indahayu.aplikasiloginregister;

import java.util.List;

public class Mainmodelgaleri {

    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        private int id;
        private String keterangan;

        private String foto;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id=" + id +
                    ", keterangan='" + keterangan + '\'' +
                    ", foto='" + foto + '\'' +
                    '}';
        }
    }
}

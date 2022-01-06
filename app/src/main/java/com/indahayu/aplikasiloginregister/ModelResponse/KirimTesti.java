package com.indahayu.aplikasiloginregister.ModelResponse;

public class KirimTesti {
    String error;
    String message;

    public KirimTesti(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


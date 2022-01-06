package com.indahayu.aplikasiloginregister.ModelResponse;

public class UpdateBukti {
    private String error;
    private String message;


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

    public UpdateBukti(String error, String message) {
        this.error = error;
        this.message = message;
    }
}

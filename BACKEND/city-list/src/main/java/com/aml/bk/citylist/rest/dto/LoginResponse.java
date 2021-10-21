package com.aml.bk.citylist.rest.dto;

public class LoginResponse {
    private boolean result;

    public LoginResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}

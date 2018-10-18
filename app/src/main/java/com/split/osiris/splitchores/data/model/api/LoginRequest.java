package com.split.osiris.splitchores.data.model.api;

public class LoginRequest {
    private final String userId;
    private final String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }


}

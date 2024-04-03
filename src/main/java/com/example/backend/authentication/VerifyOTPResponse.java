package com.example.backend.authentication;

import com.example.backend.Patient.Patient;

public class VerifyOTPResponse {
    private String status;
    private String jwtToken;
    private Patient user;

    // Constructors, getters, and setters
    public VerifyOTPResponse(String status, String jwtToken, Patient user) {
        this.status = status;
        this.jwtToken = jwtToken;
        this.user = user;
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Patient getUser() {
        return user;
    }

    public void setUser(Patient user) {
        this.user = user;
    }
}

package com.example.demo.templates;

import java.sql.Timestamp;

public class ReservationInfoResponse {

    private Timestamp expirationTime;

    private Float toPay;

    public ReservationInfoResponse(Timestamp expirationTime, Float toPay) {
        this.expirationTime = expirationTime;
        this.toPay = toPay;
    }

    public Timestamp getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Timestamp expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Float getToPay() {
        return toPay;
    }

    public void setToPay(Float toPay) {
        this.toPay = toPay;
    }
}

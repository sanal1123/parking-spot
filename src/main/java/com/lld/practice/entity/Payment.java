package com.lld.practice.entity;

import java.math.BigDecimal;
import java.time.Instant;

public class Payment {
    private String paymentId;
    private String ticketId;
    private final BigDecimal fee;
    private Instant paidAt;

    public Payment(String ticketId, BigDecimal fee) {
        this.ticketId = ticketId;
        this.fee = fee;
    }

    public void setPaidAt(Instant paidAt) {
        this.paidAt = paidAt;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "[" +
                " parkingFee: " + fee +
                " ticketId: " + ticketId +
                " paymentId: " + paymentId +
                " paidAt: " + paidAt +
                " ]";
    }
}

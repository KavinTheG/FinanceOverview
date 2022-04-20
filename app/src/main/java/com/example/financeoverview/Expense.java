package com.example.financeoverview;

import java.util.Date;

public class Expense {

    private String reason;
    private double value;
    private Date date;

    public Expense(String reason, double value, Date date) {
        this.reason = reason;
        this.value = value;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
}

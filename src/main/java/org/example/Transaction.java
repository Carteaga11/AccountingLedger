package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Transaction {
    // Instance variables to store the transaction information
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }


    // Getter and setter methods for the date attribute
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and setter methods for the time attribute
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    // Getter and setter methods for the description attribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and setter methods for the vendor attribute
    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    // Getter and setter methods for the amount attribute
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // toString method to format the transaction as a string, which can be used when writing transactions to the CSV file
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", date, time, description, vendor, amount);
    }
}


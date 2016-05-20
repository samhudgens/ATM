package com.samhudgens;

import java.util.Date;

/**
 * Created by samhudgens on 5/19/16.
 */
public class Transaction {

    String transactionType;
    String amount;
    String timeStamp;
    static int FTN = 0;
    int uniqueFTN = 0;
    String source;
    String destination;

    public Transaction(){}

    public Transaction(String transactionType, String amount, String source, String destination) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timeStamp = new Date().toString();
        this.uniqueFTN = FTN + 1;
        this.source = source;
        this.destination = destination;
        FTN++;
    }

    public Transaction(String transactionType, String amount, String source) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.timeStamp = new Date().toString();
        this.uniqueFTN = FTN + 1;
        this.source = source;
        this.destination = "420";
        FTN++;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public static int getFTN() {
        return FTN;
    }

    public int getUniqueFTN() {
        return uniqueFTN;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}

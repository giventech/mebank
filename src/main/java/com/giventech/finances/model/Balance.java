package com.giventech.finances.model;

public class Balance {

    Double relativeBalance;
    Integer transactionIncluded;

    public Double getRelativeBalance() {
        return relativeBalance;
    }

    public Integer getTransactionIncluded() {
        return transactionIncluded;
    }

    public Balance(Double relativeBalance, Integer transactionIncluded) {
        this.relativeBalance = relativeBalance;
        this.transactionIncluded = transactionIncluded;
    }
}

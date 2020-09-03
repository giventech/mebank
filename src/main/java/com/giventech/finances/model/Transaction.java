package com.giventech.finances.model;

import java.util.Date;

public  class Transaction {

    private String transactionId;
    private String fromAccountId;
    private String  toAccountId;
    private Date createdAt;
    private Double amount;
    private TransactionType TransactionType;
    private String relatedTransaction;

    public Transaction(String transactionId, String fromAccountId, String toAccountId, Date createdAt, Double amount, com.giventech.finances.model.TransactionType transactionType, String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createdAt = createdAt;
        this.amount = amount;
        TransactionType = transactionType;
        this.relatedTransaction = relatedTransaction;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return TransactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }




}

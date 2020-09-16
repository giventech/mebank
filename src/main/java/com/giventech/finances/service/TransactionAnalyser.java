package com.giventech.finances.service;
import com.giventech.finances.model.Balance;
import com.giventech.finances.model.Transaction;

import java.util.List;


public interface TransactionAnalyser {
    // TEst

    Balance getRelativeAccountBalance(String accountId, String  startDate, String  endDate);
    List<Transaction> getAllTransactions();
}

package com.giventech.finances.service;

import com.giventech.finances.model.Balance;
import com.giventech.finances.model.Transaction;
import com.giventech.finances.model.TransactionType;
import com.giventech.finances.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyserImpl  implements TransactionAnalyser{

    private List<Transaction> allTransactions;

    public TransactionAnalyserImpl(String fileName) {
        this.allTransactions = Utils.loadTransactionFromCsvFile(fileName);
    }

    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(allTransactions);
    }

    public Balance getRelativeAccountBalance(String accountId, String  startDate, String  endDate) {

        List <Transaction> dateRangedTransactions = Utils.listAccountTransactionForDateRange(accountId,startDate,endDate, this.getAllTransactions());
        List <Transaction> transactionWithoutReversals = Utils.removeReversalTransactionsFrom(this.getAllTransactions(), dateRangedTransactions);
        List <Transaction> includedTransactions  = transactionWithoutReversals.stream().filter(value -> value.getTransactionType() == TransactionType.PAYMENT).collect(Collectors.toList());

        Double balance = includedTransactions.stream().mapToDouble(value -> {
                if (value.getFromAccountId().equals(accountId)){
                    return ( 0 - value.getAmount() );
                } else if (value.getToAccountId().equals(accountId)) {
                    return value.getAmount();
                }
            return 0;
        }).reduce(0, (subtotal, element) -> subtotal + element);

        return new Balance(balance,includedTransactions.size());
    }
}

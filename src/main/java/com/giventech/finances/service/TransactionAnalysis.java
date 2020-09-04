package com.giventech.finances.service;

import com.giventech.finances.model.Balance;
import com.giventech.finances.model.Transaction;
import com.giventech.finances.model.TransactionType;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    private static String pattern = "dd/MM/yyyy HH:mm:ss";
    private static final SimpleDateFormat dateFormater = new SimpleDateFormat(pattern);
    private  List<Transaction> allTransactions;

    public TransactionAnalysis(String fileName) {
        this.allTransactions = TransactionAnalysis.loadTransactionFromCsvFile(fileName);
    }

    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(allTransactions);
    }

    public Balance getRelativeAccountBalance(String accountId, String  startDate, String  endDate) {
        List <Transaction> dateRangedTransactions = listAccountTransactionForDateRange(accountId,startDate,endDate, this.getAllTransactions());
        List <Transaction> transactionWithoutReversals = removeReverSalTransactionFrom(this.getAllTransactions(), dateRangedTransactions);
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


    public static List<Transaction> removeReverSalTransactionFrom(List <Transaction> allTransactions, List <Transaction> selectedTransaction) {
        List<Transaction> reversalTransation = allTransactions.stream().filter(transaction -> transaction.getTransactionType() == TransactionType.REVERSAL).collect(Collectors.toList());
        Predicate<Transaction> transactionPredicate = transaction -> reversalTransation.stream().anyMatch(
                transaction1 -> {
                    boolean toReverse =   transaction1.getRelatedTransaction().equals(transaction.getTransactionId());
                    return toReverse;
                });
        selectedTransaction.removeIf(transactionPredicate);
        return selectedTransaction;
    }

    public static  List<Transaction> listAccountTransactionForDateRange(String accountId, String  startDate, String  endDate, List <Transaction> transactions) {
        return transactions.stream().filter(transaction ->       {
            boolean test =false;
            try {
                //We want to consider transaction to and fromm account id
                test=  (transaction.getFromAccountId().equals(accountId) || transaction.getToAccountId().equals(accountId)) &&
                        transaction.getCreatedAt().after(dateFormater.parse(startDate)) &&
                        transaction.getCreatedAt().before(dateFormater.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return test;
        }).collect(Collectors.toList());
    }


    public static List<Transaction> loadTransactionFromCsvFile (String csvFileName)  {
        InputStream in = TransactionAnalysis.class.getResourceAsStream("/"+csvFileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        List<Transaction> transactions = null;
            transactions = reader.lines()
                    .map(line -> {
                        String linesRead[] = line.split(",");
                        Transaction transaction = null;
                        TransactionType transactionType = linesRead[5].equals("PAYMENT") ? TransactionType.PAYMENT: TransactionType.REVERSAL;

                        try {
                            transaction = new Transaction(
                                     linesRead[0],
                                     linesRead[1],
                                     linesRead[2],
                                     dateFormater.parse(linesRead[3]),
                                     new Double(linesRead[4]),
                                     transactionType,
                                     transactionType == TransactionType.PAYMENT ? null:linesRead[6]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return transaction;
                    })
                    .collect(Collectors.toList());

        return Collections.unmodifiableList(transactions);
    }
}

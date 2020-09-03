package com.giventech.finances;

import com.giventech.finances.model.Transaction;
import com.giventech.finances.model.TransactionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public  class TransactionAnalysis {
    private static String transactionCsv = "transaction.csv";
    private static String pattern = "dd/MM/yyyy HH:mm:ss";
    private static final SimpleDateFormat dateFormater = new SimpleDateFormat(pattern);


    public static double getRelativeAccountBalance(String accountId, String  startDate, String  endDate) {
        List <Transaction> allTransactions = TransactionAnalysis.loadTransactionFromCsvFile(transactionCsv);
        List <Transaction> dateRangedTransactions = listAccountTransactionForDateRange(accountId,startDate,endDate, allTransactions);
        List <Transaction> transactionWitoutReversals = removeReversalTransactionFromList(dateRangedTransactions);
        return 0.0;
    }


    public static List<Transaction> removeReversalTransactionFromList(List <Transaction> transactions) {
        List<Transaction> reversalTransation = transactions.stream().filter(transaction -> transaction.getTransactionType() == TransactionType.REVERSAL).collect(Collectors.toList());
        Predicate<Transaction> transactionPredicate = transaction -> reversalTransation.stream().anyMatch(transaction1 -> transaction.getTransactionId().equals(transaction1.getRelatedTransaction()));
        transactions.removeIf(transactionPredicate);
        return transactions;
    }

    public static  List<Transaction> listAccountTransactionForDateRange(String accountId, String  startDate, String  endDate, List <Transaction> transactions) {
        return transactions.stream().filter(transaction ->       {
            boolean test =false;
            try {
                test=
                        (transaction.getFromAccountId().equals(accountId) || transaction.getToAccountId().equals(accountId)) &&
                    transaction.getCreatedAt().after(dateFormater.parse(startDate)) &&
                    transaction.getCreatedAt().before(dateFormater.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return test;
        }).collect(Collectors.toList());
    }


    public static List<Transaction> loadTransactionFromCsvFile (String csvFileName)  {
        ClassLoader classLoader = TransactionAnalysis.class.getClassLoader();
        File transactionCsvFile = new File(classLoader.getResource(csvFileName).getFile());
        List<Transaction> transactions = null;
        try {
            transactions = Files.lines(Paths.get(transactionCsvFile.getPath()))
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO in production a copy will be returned to prevent from clients
        return Collections.unmodifiableList(transactions);
    }
}

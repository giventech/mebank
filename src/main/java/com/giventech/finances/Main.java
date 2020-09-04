package com.giventech.finances;

import com.giventech.finances.model.Balance;
import com.giventech.finances.model.Transaction;
import com.giventech.finances.service.TransactionAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main (String args[]) throws IOException {

        //Keyboard inputs
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);

        String[] words = in.readLine().split(",");
        String  accountId   = words[0];
        String  startDate   = words[1];
        String  endDate     = words[2];

        //
        String fileName =  "transaction.csv";
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis(fileName);
        Balance balance =  transactionAnalysis.getRelativeAccountBalance(accountId,startDate,endDate);
        System.out.println("Relative balance for the period is: "+balance.getRelativeBalance() + "\n");
        System.out.println("Number of transactions included is: "+ balance.getTransactionIncluded() + "\n");


    }
}

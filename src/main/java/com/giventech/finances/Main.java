package com.giventech.finances;

import com.giventech.finances.model.Balance;
import com.giventech.finances.service.TransactionAnalyser;
import com.giventech.finances.service.TransactionAnalyserImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.Locale;

public class Main {

    public static void main (String args[]) throws IOException {

        //Keyboard inputs
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);

        String[] words = in.readLine().split(",");
        String  accountId   = words[0];
        String  startDate   = words[1];
        String  endDate     = words[2];

        // Selecting the currency

        Locale locale = new Locale("en", "AU");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        //
        String fileName =  "transaction.csv";
        TransactionAnalyser transactionAnalyser = new TransactionAnalyserImpl(fileName);
        Balance balance =  transactionAnalyser.getRelativeAccountBalance(accountId,startDate,endDate);
        System.out.println("Relative balance for the period is: "+ currencyFormatter.format(balance.getRelativeBalance()) + "\n");
        System.out.println("Number of transactions included is: "+ balance.getTransactionIncluded() + "\n");


    }
}

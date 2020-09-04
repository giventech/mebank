package com.giventech.finances;

import com.giventech.finances.utils.TransactionAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main (String args[]) throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String[] words = in.readLine().split(",");
        String  accountId  = words[0];
        String  startDate  = words[1];
        String  endDate  = words[2];
        System.out.println(TransactionAnalysis.getRelativeAccountBalance(accountId,startDate,endDate));

    }
}

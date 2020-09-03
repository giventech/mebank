import com.giventech.finances.TransactionAnalysis;
import com.giventech.finances.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.List;


public class TestTransactionAnalysis {

    public static final Double REVERSAL_POSITIVE_TOTAL = 0.0;
    public static final Double PAYMENT_POSITIVE_TOTAL = 0.0;

    public static String pattern = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);

    @BeforeEach
    public void setUp () {

    }


    @Test
    public void itShouldReturnAListOfTransactions() {

        // Given
        String fileName = "transaction.csv";

        // When
        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);


        // Then

        assert(transactions != null);


    }


    @Test
    public void itShouldReturnRecordInaDateRage() {

        // Given
        String fileName = "transaction.csv";

        // When
        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);

        // Then

        assert(transactions != null);


    }

    @Test
    public  void itShouldReturnExpectedRecordsInRanger() {

        //Given
        String fileName = "transaction.csv";
        String accountId = "ACC334455";
        String startDate = "20/10/2018 12:00:00";
        String endDate = "20/10/2018 19:45:00";

        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);

        // When
        List <Transaction> slicedTransactions = TransactionAnalysis.listAccountTransactionForDateRange(accountId, startDate,endDate, transactions);

        //Then

        assert(slicedTransactions != null);
        assert(slicedTransactions.size() == 2);



    }

//    @Test
//    public void ItShouldTotalPaymentType() {
//
//        //Given
//        String startDate = "20/10/2018 12:47:55";
//        String endDate = "20/11/2018 12:47:55";
//
//        // When
//        Double total = TransactionAnalysis.getRelativeAccountBalance(startDate,endDate);
//
//
//        //Then
//
//        assert(total == 0);
//
//
//    }



}




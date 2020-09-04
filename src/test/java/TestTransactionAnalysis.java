import com.giventech.finances.utils.TransactionAnalysis;
import com.giventech.finances.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

public class TestTransactionAnalysis {

    public static String fileName = "transaction.csv";
    public static String accountId = "ACC334455";
    public static String startDate = "20/10/2018 12:00:00";
    public static  String endDate = "20/10/2018 19:46:00";

    @BeforeEach
        public void setUp () {
    }

    @Test
    public void itShouldReturnAListOfTransactions() {

        //Given
        String fileName = TestTransactionAnalysis.fileName;

        //When
        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);

        //Then
        assert(transactions != null);
    }

    @Test
    public void itShouldReturnRecordInaDateRage() {
        //Given
        String fileName = TestTransactionAnalysis.fileName;

        //When
        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);

        //Then
        assert(transactions != null);
    }

    @Test
    public  void itShouldReturnExpectedRecordsInRanger() {

        //Given
        String fileName = TestTransactionAnalysis.fileName;
        String accountId = TestTransactionAnalysis.accountId;
        String startDate = TestTransactionAnalysis.startDate;
        String endDate = TestTransactionAnalysis.endDate;

        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);

        //When
        List <Transaction> slicedTransactions = TransactionAnalysis.listAccountTransactionForDateRange(accountId, startDate,endDate, transactions);

        //Then
        assert(slicedTransactions != null);
        assert(slicedTransactions.size() == 3);
    }

    @Test
    public  void itShouldRemoveReverSalTransactions() {
        //Given
        String fileName = TestTransactionAnalysis.fileName;
        String accountId = TestTransactionAnalysis.accountId;
        String startDate = TestTransactionAnalysis.startDate;
        String endDate = TestTransactionAnalysis.endDate;
        List <Transaction> transactions  = TransactionAnalysis.loadTransactionFromCsvFile(fileName);
        List <Transaction> slicedTransactions = TransactionAnalysis.listAccountTransactionForDateRange(accountId, startDate,endDate, transactions);

        // When
        List<Transaction> reversalProcessed= TransactionAnalysis.removeReversalTransactionFromList(slicedTransactions);

        //Then
        assert(reversalProcessed != null);
        assert(reversalProcessed.size() == 2);
    }


    @Test
    public void ItShouldTotalPaymentType() {

        //Given
        String accountId = TestTransactionAnalysis.accountId;
        String startDate = TestTransactionAnalysis.startDate;
        String endDate = TestTransactionAnalysis.endDate;

        // When
        Double total = TransactionAnalysis.getRelativeAccountBalance(accountId, startDate,endDate);

        //Then
        assert(total == -25.00);
    }

    @Test
    public void totalShoudBeFiveDollarAndFiftyCents() {
        //Given
        String accountId = "ACC998877";
        String startDate = "20/10/2018 12:47:55,";
        String endDate = "20/10/2018 18:45:00";

        // When
        Double total = TransactionAnalysis.getRelativeAccountBalance(accountId, startDate,endDate);

        //Then
        assert(total == 5.50);
    }
}




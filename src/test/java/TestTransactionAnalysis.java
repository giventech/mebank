import com.giventech.finances.model.Balance;
import com.giventech.finances.service.TransactionAnalyser;
import com.giventech.finances.service.TransactionAnalyserImpl;
import com.giventech.finances.model.Transaction;
import com.giventech.finances.utils.Utils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

public class TestTransactionAnalysis {

    public static String fileName = "transaction.csv";
    public static String accountId = "ACC334455";
    public static String startDate = "20/10/2018 12:00:00";
    public static String endDate = "20/10/2018 19:00:00";

    @BeforeEach
    public void setUp () {
    }

    @Test
    public void itShouldReturnAListOfTransactions() {

        //Given
        String fileName = TestTransactionAnalysis.fileName;

        //When
        List <Transaction> transactions  = Utils.loadTransactionFromCsvFile(fileName);

        //Then
        assert(transactions != null);
    }

    @Test
    public void itShouldReturnRecordInaDateRage() {
        //Given
        String fileName = TestTransactionAnalysis.fileName;

        //When
        List <Transaction> transactions  = Utils.loadTransactionFromCsvFile(fileName);

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

        List <Transaction> transactions  = Utils.loadTransactionFromCsvFile(fileName);

        //When
        List <Transaction> slicedTransactions = Utils.listAccountTransactionForDateRange(accountId, startDate,endDate, transactions);

        //Then
        assert(slicedTransactions != null);
        assert(slicedTransactions.size() == 2);
    }

    @Test
    public  void itShouldRemoveReverSalTransactions() {
        //Given
        String fileName = TestTransactionAnalysis.fileName;
        String accountId = TestTransactionAnalysis.accountId;
        String startDate = TestTransactionAnalysis.startDate;
        String endDate = TestTransactionAnalysis.endDate;

        List <Transaction> transactions  = Utils.loadTransactionFromCsvFile(fileName);
        List <Transaction> slicedTransactions = Utils.listAccountTransactionForDateRange(accountId, startDate,endDate, transactions);

        // When
        List<Transaction> reversalProcessed= Utils.removeReversalTransactionsFrom(transactions,slicedTransactions);

        //Then
        assert(reversalProcessed != null);
        assert(reversalProcessed.size() == 1);
    }


    @Test
    public void ItShouldTotalPaymentType() {

        //Given
        String accountId = TestTransactionAnalysis.accountId;
        String startDate = TestTransactionAnalysis.startDate;
        String endDate = TestTransactionAnalysis.endDate;
        String fileName = TestTransactionAnalysis.fileName;
        TransactionAnalyser transactionAnalyserImpl = new TransactionAnalyserImpl(fileName);

        // When
        Balance balance = transactionAnalyserImpl.getRelativeAccountBalance(accountId, startDate,endDate);

        //Then
        assert(balance.getRelativeBalance() == -25.00);
        assert(balance.getTransactionIncluded() == 1);
    }

    @Test
    public void totalShoudBeMinusFiveDollarAndFiftyCents() {
        //Given
        String accountId = "ACC998877";
        String startDate = "20/10/2018 12:47:55";
        String endDate = "20/10/2018 18:45:00";
        TransactionAnalyser transactionAnalyserImpl = new TransactionAnalyserImpl(fileName);

        // When
        Balance balance = transactionAnalyserImpl.getRelativeAccountBalance(accountId, startDate,endDate);

        //Then
        assert(balance.getRelativeBalance() == -5);
        assert(balance.getTransactionIncluded() == 1);
    }


    @Test
    public void itShouldLoadAllTheTransactionsInMemory() {
        //Given
        String fileName = TestTransactionAnalysis.fileName;
        TransactionAnalyser transactions;

        // When
        transactions = new TransactionAnalyserImpl(fileName);

        //Then
        assert(transactions !=null );
        assert(transactions.getAllTransactions().size() == 5);
    }
}




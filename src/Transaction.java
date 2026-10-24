import java.time.LocalDateTime;

public class Transaction {
    private String transactionID;
    private int accountNumber;
    private double amount;
    private String transactionType;
    private LocalDateTime timestamp;
   
    public Transaction(String transcationID, int accountNumber, double amount,
    String transactionType, LocalDateTime timestamp){
        this.transactionID = transcationID;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = timestamp;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString(){
        return String.format("%s:%n %s: %s%n %s: %d%n %s: %f%n %s: %s%n %s: %s%n"
        ,"Transaction Details: ", "Transaction ID: ", getTransactionID() , 
        "Account Number: " , getAccountNumber(),"Amount: ", getAmount(),"Transcation Type: ",
        getTransactionType(), "Time Stamp: ", getTimestamp());
    }
}
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ATM {
    private List<User> users;

    public ATM() {
        users = new ArrayList<>();
        loadUsers(); // Load users from file
    }

    // Load users from a file
    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_credentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String userID = userData[0];
                String PIN = userData[1];
                User user = new User(userID, PIN);
                loadUserAccounts(user); // Load accounts for this user
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load user accounts from file
    private void loadUserAccounts(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader(user.getUserID() + "_accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String accountNumber = accountData[0];
                double balance = Double.parseDouble(accountData[1]);
                String accountHolderName = accountData[2];
                CheckAccount account = new CheckAccount(accountNumber, accountHolderName, balance, 1000); // Example
                user.addAccount(account);
                loadTransactions(account); // Load transactions for this account
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load transactions for an account
    private void loadTransactions(Account account) {
        try (BufferedReader reader = new BufferedReader(new FileReader(account.getAccountNumber() + "_transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transactionData = line.split(",");
                String transactionID = transactionData[0];
                double amount = Double.parseDouble(transactionData[1]);
                String type = transactionData[2];
                LocalDateTime timestamp = LocalDateTime.parse(transactionData[3]);
                Transaction transaction = new Transaction(transactionID, Integer.parseInt(account.getAccountNumber()), amount, type, timestamp);
                account.addTransaction(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Login method
    public User login(String userID, String PIN) {
        for (User user : users) {
            if (userID.equals(user.getUserID()) && user.authenticate(PIN)) {
                return user;
            }
        }
        return null;
    }

    // Perform a transaction (Deposit/Withdraw)
    public void performTransaction(Account account, String type, double amount) {
    switch (type.toLowerCase()) {
        case "deposit":
            account.deposit(amount);
            account.addTransaction(new Transaction(
                "TXN" + System.currentTimeMillis(),
                Integer.parseInt(account.getAccountNumber()),
                amount,
                "Deposit",
                java.time.LocalDateTime.now()
            ));
            saveAccountData(account);
            saveTransactionHistory(account);
            break;

        case "withdraw":
            account.withdraw(amount);
            account.addTransaction(new Transaction(
                "TXN" + System.currentTimeMillis(),
                Integer.parseInt(account.getAccountNumber()),
                amount,
                "Withdrawal",
                java.time.LocalDateTime.now()
            ));
            saveAccountData(account);
            saveTransactionHistory(account);
            break;

        default:
            throw new IllegalArgumentException("Invalid transaction type.");
    }
}

    // Save account data to file
    private void saveAccountData(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(account.getAccountNumber() + "_accounts.txt"))) {
            writer.write(account.getAccountNumber() + "," + account.getBalance() + "," + account.getAccountHolderName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save transaction history to file
    public void saveTransactionHistory(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(account.getAccountNumber() + "_transactions.txt", true))) {
            for (Transaction transaction : account.getTransactions()) {
                writer.write(transaction.getTransactionID() + "," +
                        transaction.getAmount() + "," +
                        transaction.getTransactionType() + "," +
                        transaction.getTimestamp() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        users.add(user);
    }
}

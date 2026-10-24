import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ATMSystemTest {
    public static void main(String[] args) {
        ATM atm = new ATM();
    
        // Create a user with a checking account
        User user1 = new User("1234", "1111");
        CheckAccount account1 = new CheckAccount("1001", "Osama Abutaha", 5000, 1000);

        User user2 = new User("Joman", "2222");
        CheckAccount account2 = new CheckAccount("1002","Joman Alsharbini" , 10000, 0);

        user1.addAccount(account1);
        atm.addUser(user1);

        user2.addAccount(account2);
        atm.addUser(user2);
    
        // Show the login screen
        showLoginScreen(atm);
    }
    
    private static void showLoginScreen(ATM atm) {
        JFrame frame = new JFrame("ATM Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(670,440);
        frame.setSize(800, 400);
    
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBackground(Color.BLUE);
    
        JLabel userIDLabel = new JLabel("User ID / User Name:");
        userIDLabel.setForeground(Color.WHITE);
        JTextField userIDField = new JTextField();
        userIDField.setBackground(Color.BLACK);
        userIDField.setForeground(Color.WHITE);
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setForeground(Color.WHITE);
        JPasswordField pinField = new JPasswordField();
        pinField.setBackground(Color.BLACK);
        pinField.setForeground(Color.WHITE);


        JButton loginButton = new JButton("Login");
        loginButton.setForeground(Color.RED);

    
        loginButton.addActionListener(e -> {
            String userID = userIDField.getText();
            String PIN = new String(pinField.getPassword());
    
            User loggedInUser = atm.login(userID, PIN);
            if (loggedInUser != null) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose(); // Close login frame
                showMainMenu(atm, loggedInUser); // Show main menu
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials. Try again.");
            }
        });
    
        panel.add(userIDLabel);
        panel.add(userIDField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(new JLabel()); // Placeholder for alignment
        panel.add(loginButton);
    
        frame.add(panel);
        frame.setVisible(true);
    }
    

    private static void showMainMenu(ATM atm, User user) {
        JFrame frame = new JFrame("ATM Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocation(670,440);
    
        //JPanel panel = new JPanel(new GridLayout(5, 1));
        JPanel panel = new JPanel(new GridLayout());

    
        JButton balanceInquiryButton = new JButton("Balance Inquiry");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton viewTransactionsButton = new JButton("View Transactions");
        JButton exitButton = new JButton("Exit");
    
        balanceInquiryButton.addActionListener(e -> {
            Account account = user.getAccounts().get(0); // Assuming one account
            JOptionPane.showMessageDialog(frame, "Account Balance: $" + account.getBalance());
        });
    
        depositButton.addActionListener(e -> {
            Account account = user.getAccounts().get(0);
            String depositAmountStr = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(depositAmountStr);
                atm.performTransaction(account, "deposit", amount);
                JOptionPane.showMessageDialog(frame, "Deposit successful! New balance: $" + account.getBalance());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please try again.");
            }
        });
    
        withdrawButton.addActionListener(e -> {
            Account account = user.getAccounts().get(0);
            String withdrawAmountStr = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(withdrawAmountStr);
                if (amount > 0) {
                    atm.performTransaction(account, "withdraw", amount);
                    JOptionPane.showMessageDialog(frame, "Withdrawal successful! New balance: $" + account.getBalance());
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please try again.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount. Please try again.");
            }
        });
    
        viewTransactionsButton.addActionListener(e -> {
            Account account = user.getAccounts().get(0);
            List<Transaction> transactions = account.getTransactions();
            StringBuilder transactionHistory = new StringBuilder("Transaction History:\n");
            for (Transaction transaction : transactions) {
                transactionHistory.append(transaction.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, transactionHistory.toString());
        });
    
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using the ATM!");
            frame.dispose();
        });
    
        panel.add(balanceInquiryButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(viewTransactionsButton);
        panel.add(exitButton);
    
        frame.add(panel);
        frame.setVisible(true);
    }
}    
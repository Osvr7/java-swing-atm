import java.util.ArrayList;
import java.util.List;

public class Admin {

    private List<User> users;

    public Admin() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void createAccount(User user, Account account) {
        user.addAccount(account);
    }

    public User findUserByID(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    public void resetUserPIN(String userID, String newPIN) {
        User user = findUserByID(userID);
        if (user != null) {
            user.setPIN(newPIN);
            System.out.println("PIN reset successfully for user: " + userID);
        } else {
            System.out.println("User not found.");
        }
    }

    public void generateReport(List<User> users) {
        System.out.println("Generating report...");
        for (User user : users) {
            for (Account account : user.getAccounts()) {
                for (Transaction transaction : account.getTransactions()) {
                    System.out.println(transaction);
                }
            }
        }
    }
    
}

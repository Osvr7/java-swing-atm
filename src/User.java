import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private String PIN;
    private List<Account> accounts;
    private boolean isLocked;    

    public User(String userID, String PIN){
        this.userID = userID;
        this.PIN = PIN;
        this.accounts = new ArrayList<>();
        this.isLocked = false;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID(){
        return userID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void viewAccounts() {
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber() + 
                               ", Balance: " + account.getBalance() +
                               ", Account Holder: " + account.getAccountHolderName());
            account.displayAccountType();
        }
    }

    public boolean authenticate(String inputPIN) {
        return !isLocked && this.PIN.equals(inputPIN);
    }

    @Override
    public String toString() {
        return "User [userID=" + getUserID() + ", PIN()=" + getPIN() + ", Accounts()=" + getAccounts() + "]";
    }
    
}

public class CheckAccount extends Account{
    private double overdraftLimit;

    public CheckAccount(String accountNumber, String accountHolderName,
    double balance, double overdraftLimit){
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit >= 0) {
            this.overdraftLimit = overdraftLimit;
        } else {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= (getBalance() + overdraftLimit) && amount > 0) {
            double newBalance = getBalance() - amount;
            if (newBalance < 0) {
                overdraftLimit += newBalance; // Reduce overdraft limit
            } else {
                super.withdraw(amount);
            }
        } else {
            throw new IllegalArgumentException("Withdrawal exceeds overdraft limit or invalid amount.");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: Checking");
    }

}
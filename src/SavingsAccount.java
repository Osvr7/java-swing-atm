public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName,
    double balance, double interestRate){
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }  

    public void intrestCalculator(){
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: Savings");
    }
}
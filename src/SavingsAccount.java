public class SavingsAccount extends BankAccount {
    public SavingsAccount(double amount) {
        super(amount);
    }
    @Override
    public double getBalance() {
        System.out.println("Getting balance from Savings Account");
        return super.getBalance();
    }
    public void withdraw(double amount) {
        if(amount > getBalance()) {
            System.out.println("Cannot withdraw more than current balance!");
            return;
        }
        System.out.printf("Withdrawing %.2f from Savings Account\n", amount);
        super.withdraw(amount);
    }
    public void deposit(double amount) {
        System.out.printf("Depositing %.2f from Savings Account\n", amount);
        super.deposit(amount);
    }
}

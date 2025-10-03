public class CheckingAccount extends BankAccount {
    private final double overdraft = 200;
    public CheckingAccount(double amount) {
        super(amount);
    }
    @Override
    public double getBalance() {
        System.out.println("Getting balance from Checking Account");
        return super.getBalance();
    }
    public void withdraw(double amount) {
        if(amount > (overdraft + getBalance())) {
            System.out.println("Cannot withdraw more than the balance & overdraft!");
            return;
        }
        System.out.printf("Withdrawing %.2f from Checking Account\n", amount);
        super.withdraw(amount);
    }
    public void deposit(double amount) {
        System.out.printf("Depositing %.2f from Checking Account\n", amount);
        super.deposit(amount);
    }
}

import java.util.HashMap;
import java.util.Map;

public class Manager {
    Input input = new Input();
    private final Map<String, BankAccount> accounts;
    public Manager() {
        accounts = new HashMap<>();
        showHomeInterface();
    }
    public void showHomeInterface() {
        int choice;
        do {
            System.out.println("--- Bank Account Manager ---");
            System.out.println("0) Exit");
            System.out.println("1) Show Accounts");
            System.out.println("2) Create Account");
            choice = input.getInteger("Choice", 0, 2);
            // avoid any input bug
            input.clear();
            switch(choice) {
                case 1:
                    listAccounts();
                    break;
                case 2:
                    addAccount();
                    break;
                case 0:
                default:
                    break;
            }
        } while (choice != 0);
        input.close();
    }
    public void listAccounts() {
        int i = 0;
        String[] accountNames = new String[accounts.size()];
        if(accountNames.length == 0) {
            System.out.println("No accounts found...");
            return;
        }
        System.out.println("0) Exit");
        for(String accountName : accounts.keySet()) {
            BankAccount account = accounts.get(accountName);
            String type = (account instanceof CheckingAccount) ? "Checking" : "Savings";
            accountNames[i] = accountName;
            System.out.printf("%d) %s (%s)\n", ++i, accountName, type);
        }
        int choice = input.getInteger("Choose account", 0, i);
        if(choice == 0) return;
        manageAccount(accountNames[i-1]);
    }
    public void manageAccount(String accountName) {
        BankAccount account = accounts.get(accountName);
        double balance = account.getBalance();
        System.out.printf("Actions for %s (Balance: %.2f)\n", accountName, balance);
        System.out.println("0) Exit");
        System.out.println("1) Withdraw");
        System.out.println("2) Deposit");
        System.out.println("3) Delete");
        int choice = input.getInteger("Choice", 0, 3);
        switch (choice) {
            case 1:
                account.withdraw(input.getDouble("Enter amount to withdraw"));
                break;
            case 2:
                account.deposit(input.getDouble("Enter amount to deposit"));
                break;
            case 3:
                System.out.println("Withdrawing the account's amount in cash before deleting...");
                account.withdraw(account.getBalance());
                accounts.remove(accountName);
                System.out.println("Account deleted!");
                break;
            case 0:
            default:
                break;
        }
    }
    public void addAccount() {
        String name;
        while(true) {
            name = input.getString("Enter the account name");
            if(accounts.containsKey(name)) System.out.println("Account name exists, please input a different name!");
            else break;
        }
        System.out.println("Types:");
        System.out.println("1) Checking");
        System.out.println("2) Savings");
        int type = input.getInteger("Choose an account type", 1, 2);
        double amount = input.getDouble("Enter initial amount to deposit");
        BankAccount account = (type == 1) ? new CheckingAccount(amount) : new SavingsAccount(amount);
        accounts.put(name, account);
        System.out.printf("Your %s account \"%s\" has been created!\nBalance: %.2f\n", (type == 1 ? "checking" : "savings"), name, amount);
    }

}

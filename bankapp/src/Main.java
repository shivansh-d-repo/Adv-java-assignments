import model.Account;
import service.AccountManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1: Add Account\n2: Deposit\n3: Withdraw\n4: Display Account Details\n5: Calculate Interest\n6: Get Account Count\n7: Get Balance\n8: Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("Account Number: ");
                    String accNum = sc.nextLine();
                    System.out.print("Account Holder Name: ");
                    String accName = sc.nextLine();
                    System.out.print("Initial Balance: ");
                    double bal = sc.nextDouble();
                    Account acc = new Account(accNum, accName, bal);
                    accountManager.addAccount(acc);
                    System.out.println("Account added to " + acc.getBankName() + "." );
                    break;
                case 2:
                    System.out.print("Account Number: ");
                    accNum = sc.nextLine();
                    Account depositAcc = accountManager.getAccount(accNum);
                    if (depositAcc != null) {
                        System.out.print("Amount to deposit: ");
                        double amt = sc.nextDouble();
                        depositAcc.deposit(amt);
                        System.out.println("Deposited.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Account Number: ");
                    accNum = sc.nextLine();
                    Account withdrawAcc = accountManager.getAccount(accNum);
                    if (withdrawAcc != null) {
                        System.out.print("Amount to withdraw: ");
                        double amt = sc.nextDouble();
                        withdrawAcc.withdraw(amt);
                        System.out.println("Withdrawn.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    for (Account a : accountManager.getAllAccounts()) {
                        System.out.println("Account Number: " + a.getAccountNumber() +
                                ", Holder: " + a.getAccountHolderName() +
                                ", Balance: " + a.getBalance());
                    }
                    break;
                case 5:
                    System.out.print("Account Number: ");
                    accNum = sc.nextLine();
                    Account interestAcc = accountManager.getAccount(accNum);
                    if (interestAcc != null) {
                        System.out.println("Interest: " + interestAcc.calculateInterest());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.println("Total Accounts: " + Account.getAccountCount());
                    break;
                case 7:
                    System.out.print("Account Number: ");
                    accNum = sc.nextLine();
                    Account balAcc = accountManager.getAccount(accNum);
                    if (balAcc != null) {
                        System.out.println("Balance: " + balAcc.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            sc.nextLine();
        }
        sc.close();
    }
}
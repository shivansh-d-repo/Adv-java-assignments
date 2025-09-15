package model;

import util.Transaction;

public class Account extends BankAccount implements Transaction {
    private double balance;
    public static int accountCount = 0;
    public static final String BANK_NAME = "MyBank";

    public Account(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName);
        this.balance = balance;
        accountCount++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    @Override
    public double calculateInterest() {
        // Example: 3% annual interest
        return balance * 0.03;
    }

    public final String getBankName() {
        return BANK_NAME;
    }

    public static int getAccountCount() {
        return accountCount;
    }
}
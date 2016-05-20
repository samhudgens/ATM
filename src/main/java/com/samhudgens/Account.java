package com.samhudgens;

/**
 * Created by samhudgens on 5/19/16.
 */
public class Account {

    Account() {};

    public enum AccountStatus { OPEN, CLOSED, FROZEN }
    public enum OverdraftPrevention { ENABLED, DISABLED, AUTOTRANSFER }

    private String accountHolderName;
    //private AccountType accountType;
    private int accountNumber;
    private AccountStatus accountStatus;
    private OverdraftPrevention overdraftPrevention;
    private double balance;
    private double interestRate;

    public Account(String accountHolderName, int accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.accountStatus = AccountStatus.OPEN;
        this.overdraftPrevention = OverdraftPrevention.ENABLED;
        this.balance = 0.00;
        this.interestRate = 0.0006;
    }

    public void removeDebit(double amount) {
        this.balance -= amount;
    }

    public void addCredit(double amount) {
        this.balance += amount;
    }




    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public OverdraftPrevention getOverdraftPrevention() {
        return overdraftPrevention;
    }

    public void setOverdraftPrevention(OverdraftPrevention overdraftPrevention) {
        this.overdraftPrevention = overdraftPrevention;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}

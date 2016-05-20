package com.samhudgens;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by samhudgens on 5/19/16.
 */
public class Bank {

    static ArrayList<Account> accountList;
    static ArrayList<Transaction> ledger;

    Account currentAccount;

    double amountChoice;
    int accountNumberChoice;
    String whateverChoice;

    private boolean atmOn;

    public Bank() {
        accountList = new ArrayList<Account>();
        ledger = new ArrayList<Transaction>();
        this.atmOn = true;
    }

    public Account createAccount() {
        System.out.println("Welcome! Create your account! Please enter your name");
        Scanner input = new Scanner(System.in);
        String name = input.next();
        System.out.println("Please choose an account number");
        int number = input.nextInt();
        Account userAccount = new Account(name, number);
        accountList.add(userAccount);
        return userAccount;
    }

    public void printOptions(){
        System.out.println("Choose an option: \n" +
                "0: Set Balance\n" +
                "2: Deposit Money\n" +
                "4: Withdraw Money\n" +
                "5: Set Interest Rate\n" +
                "6: Change account status\n" +
                "7: Show transaction history (currently unavailable)\n" +
                "8: Change Overdraft Protection Setting\n" +
                "9: Transfer Money\n" +
                "11: Account Selection\n" +
                "12: Balance Inquiry\n" +
                "13: Exit ATM\n");
    }


    public int promptUserforInteger() {
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        return choice;
    }

    public double promptUserforDouble() {
        Scanner input = new Scanner(System.in);
        double choice = input.nextDouble();
        return choice;
    }

    public String promptUserforString() {
        Scanner input = new Scanner(System.in);
        String choice = input.next();
        return choice;
    }

    public void commandLineInterface(){
        Account existingAccount = new Account("Bob", 80);
        currentAccount = createAccount();
        while(atmOn) {
            printOptions();
            int choice = promptUserforInteger();
            if(isClosed(currentAccount)) {
                if(choice != 6) {
                    break;
                }
            }
            switch(choice) {
                case 0:
                    System.out.println("Set your balance");
                    amountChoice = promptUserforDouble();
                    currentAccount.setBalance(amountChoice);
                    break;
                case 1:
                    break;
                case 2:
                    System.out.println("How much would you like to deposit?");
                    amountChoice = promptUserforDouble();
                    deposit(amountChoice, currentAccount);
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("How much would you like to withdraw?");
                    amountChoice = promptUserforDouble();
                    withdrawal(amountChoice, currentAccount);
                    break;
                case 5:
                    System.out.println("Choose how much interest you want your account to earn!");
                    amountChoice = promptUserforDouble();
                    currentAccount.setInterestRate(amountChoice);
                    break;
                case 6:
                    System.out.println("Open or close your account? Type the word open or the word close");
                    whateverChoice = promptUserforString();
                    boolean stillAsking = true;
                    while(stillAsking)
                    {if(whateverChoice == "open") {
                        isOpen(currentAccount);
                        stillAsking = false;
                    } else if(whateverChoice == "close") {
                        if(currentAccount.getBalance() == 0) {
                            isClosed(currentAccount);
                            stillAsking = false;
                        } else {
                            System.out.println("If you want to close your account, set your account to open");
                        }
                    } else {
                        System.out.println("Please type open or close");
                        whateverChoice = promptUserforString();
                    }}
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Change overdraft protection settings. Type 'enable', 'disable', or 'autotransfer'");
                    whateverChoice = promptUserforString();
                    boolean stillAsking2 = true;
                    while(stillAsking2) {
                        if(whateverChoice == "enable") {
                            isOverdraftPreventionEnabled(currentAccount);
                            stillAsking2 = false;
                        } else if(whateverChoice == "disable") {
                            isOverdraftPreventionDisabled(currentAccount);
                            stillAsking2 = false;
                        }
                    }
                    break;
                case 9:
                    System.out.println("Choose an account you want to transfer money to");
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    atmOn = false;
                    System.out.println("Session closed. Please come again and create a new account and tons of fake money whenever you want");
                    break;
            }
        }
    }

    public boolean isOpen(Account currentAccount) {
        if (currentAccount.getAccountStatus() == Account.AccountStatus.OPEN) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isClosed(Account currentAccount) {
        if (currentAccount.getAccountStatus() == Account.AccountStatus.CLOSED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFrozen(Account currentAccount) {
        if (currentAccount.getAccountStatus() == Account.AccountStatus.FROZEN) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOverdraftPreventionEnabled(Account currentAccount) {
        if (currentAccount.getOverdraftPrevention() == Account.OverdraftPrevention.ENABLED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOverdraftPreventionDisabled(Account currentAccount) {
        if (currentAccount.getOverdraftPrevention() == Account.OverdraftPrevention.DISABLED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOverdraftPreventionAutoTransfer(Account currentAccount) {
        if (currentAccount.getOverdraftPrevention() == Account.OverdraftPrevention.AUTOTRANSFER) {
            return true;
        } else {
            return false;
        }
    }

    public void withdrawal(double amount, Account sourceAccount) {
        if(isOpen(sourceAccount)){
            if(sourceAccount.getBalance() >= amount) {
                sourceAccount.removeDebit(amount);
                System.out.println("Debit approved, account balance is now " + sourceAccount.getBalance());
            } else {
                if(isOverdraftPreventionDisabled(sourceAccount)) {
                    sourceAccount.removeDebit(amount);
                    System.out.println("Debit approved, account balance is now " + sourceAccount.getBalance());
                }
                if(isOverdraftPreventionEnabled(sourceAccount)) {
                    System.out.println("Debit not approved -- debit amount would overdraw account");
                }
                if(isOverdraftPreventionAutoTransfer(sourceAccount)){
                    System.out.println("Unable to complete debit request -- Your overdraft prevention is supposed to automatically transfer money from a linked account, but we haven't figured out how to program that yet.");
                }
            }
        } else if(isClosed(sourceAccount) || isFrozen(sourceAccount)) {
            System.out.println("Cannot perform debit -- account is not open");
        }
        ledger.add(new Transaction("withdrawal", Double.toString(amount), Integer.toString(sourceAccount.getAccountNumber())));
    }

    public void deposit(double amount, Account targetAccount) {
        if(isOpen(targetAccount)) {
            targetAccount.addCredit(amount);
            System.out.println("Credit approved, balance is now " + targetAccount.getBalance());
        } else {
            System.out.println("Unable to credit account -- account is not open");
        }
        ledger.add(new Transaction("deposit", Double.toString(amount), Integer.toString(targetAccount.getAccountNumber())));
    }

    public void transferFundsFromThisAccountToAnother(Account source, Account target, double amount) {
        withdrawal(amount, source);
        deposit(amount, target);
        ledger.add(new Transaction("transfer", Double.toString(amount), Integer.toString(source.getAccountNumber()), Integer.toString(target.getAccountNumber())));
    }

}

package com.samhudgens;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by samhudgens on 5/20/16.
 */
public class BankSpec {

    Account bobsAccount;
    Account princesAccount;
    Bank bank;

    @Before
    public void initialize() {
        bank = new Bank();
        bobsAccount = new Account("bob", 777);
        princesAccount = new Account ("prince", 888);
        bobsAccount.setBalance(1000);
        princesAccount.setBalance(1000);
    }

    @Test
    public void OpenAccountWithdrawalTest() {
        bank.withdrawal(200, bobsAccount);
        double expected = 800.0;
        double actual = bobsAccount.getBalance();
        assertEquals("balance should be 800.0", expected, actual, 0);
    }

    @Test
    public void ClosedAccountWithdrawalTest() {
        bobsAccount.setAccountStatus(Account.AccountStatus.CLOSED);
        bank.withdrawal(200, bobsAccount);
        double expected = 1000.0;
        double actual = bobsAccount.getBalance();
        assertEquals("should deny withdrawal and balance should remain the same", expected, actual, 0);
    }

    @Test
    public void OverDraftProtectionEnabledWithdrawalTest() {
        bobsAccount.setOverdraftPrevention(Account.OverdraftPrevention.ENABLED);
        bank.withdrawal(1200, bobsAccount);
        double expected = 1000.0;
        double actual = bobsAccount.getBalance();
        assertEquals("should deny debit and keep balance the same", expected, actual, 0);
    }

    @Test
    public void ClosedAccountDepositTest() {
        bobsAccount.setAccountStatus(Account.AccountStatus.CLOSED);
        bank.deposit(800, bobsAccount);
        double expected = 1000;
        double actual = bobsAccount.getBalance();
        assertEquals("should deny deposit and keep balance the same", expected, actual, 0);
    }

    @Test
    public void OpenAccountDepositTest() {
        bank.deposit(800, bobsAccount);
        double expected = 1800.0;
        double actual = bobsAccount.getBalance();
        assertEquals("should approve deposit and update balance", expected, actual, 0);
    }

    @Test
    public void TransferTest() {
        bank.transferFundsFromThisAccountToAnother(bobsAccount, princesAccount, 200);
        double expectedBobBalance = 800;
        double expectedPrinceBalance = 1200;
        double actualBobBalance = bobsAccount.getBalance();
        double actualPrinceBalance = princesAccount.getBalance();
        assertEquals("bob's account should be debited", expectedBobBalance, actualBobBalance, 0);
        assertEquals("prince's account should be credited", expectedPrinceBalance, actualPrinceBalance, 0);
    }
}

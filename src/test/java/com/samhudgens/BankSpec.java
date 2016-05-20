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

//    @Test
//    public void ClosedAccountWithdrawalTest() {
//        bank.isClosed(bobsAccount);
//        bank.withdrawal(200, bobsAccount);
//        double expected = 1000.0;
//        double actual = bobsAccount.getBalance();
//        assertEquals("balance should remain the same", expected, actual, 0);
//    }
}

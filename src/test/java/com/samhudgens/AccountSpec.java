package com.samhudgens;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by samhudgens on 5/20/16.
 */
public class AccountSpec {

    Account account;

    @Test
    public void removeDebitTest() {
        account = new Account("bob", 777);
        account.setBalance(100);
        account.removeDebit(50);
        double expected = 50.0;
        double actual = account.getBalance();
        assertEquals("balance should be 50.0", expected, actual, 0.00001);
    }

    @Test
    public void addCreditTest() {
        account = new Account("bob", 777);
        account.setBalance(100);
        account.addCredit(50);
        double expected = 150.0;
        double actual = account.getBalance();
        assertEquals("balance should be 150", expected, actual, 0.00001);
    }

}

package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CurrencyTest {

    CurrencyDatabase currencyDatabase;
    Currency currency;

    @Before
    public void setup() throws Exception{
        currencyDatabase = new CurrencyDatabase();
        currency = new Currency("KVN","KevinBucks",true);
    }

    @After
    public void teardown(){
        currencyDatabase = null;
        currency = null;
    }

    @Test
    public void testFromString(){
        //Exercise

        //Verify
    }
}

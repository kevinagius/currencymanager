package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertEquals;

public class CurrencyDatabaseTest {

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
    public void testInit() throws Exception{


    }

    @Test
    public void testGetCurrencyByCode() throws Exception{
        //Setup
        currencyDatabase.addCurrency(currency);

        //Exercise
        currencyDatabase.getCurrencyByCode("KVN");

        //Verify
        assertEquals("KVN", currencyDatabase.getCurrencyByCode("KVN").code);
    }

    @Test
    public void testCurrencyExists() throws Exception{
        //Setup
        currencyDatabase.addCurrency(currency);

        //Exercise
        currencyDatabase.currencyExists("KVN");

        //Verify
        assertTrue(currencyDatabase.currencyExists("KVN"));
    }

    @Test
    public void testGetCurrencies() throws Exception{

    }

    @Test
    public void testGetMajorCurrencies() throws Exception{


    }

    @Test
    public void testGetExchangeRate() throws Exception{


    }

    @Test
    public void testAddCurrency() throws Exception{
        //Exercise
        currencyDatabase.addCurrency(currency);

        //Verify
        assertTrue(currencyDatabase.currencyExists("KVN"));
    }

    @Test
    public void testDeleteCurrency() throws Exception{
        //Setup
        currencyDatabase.addCurrency(currency);

        //Exercise
        currencyDatabase.deleteCurrency("KVN");

        //Verify
        assertFalse(currencyDatabase.currencyExists("KVN"));
    }

    @Test
    public void testPersist() throws Exception{


    }
}

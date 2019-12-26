package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDatabaseTest {

    CurrencyDatabase currencyDatabase;
    Currency currency;

    Currency currency0 = new Currency("KVN","KevinBucks",true);
    Currency currency1 = new Currency("KTH","KeithBucks",true);

    @Before
    public void setup() throws Exception{
        currencyDatabase = new CurrencyDatabase();
        currencyDatabase.addCurrency(currency0);
        currencyDatabase.addCurrency(currency1);

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
        currencyDatabase.addCurrency(currency0);

        //Exercise
        currencyDatabase.getCurrencyByCode("KVN");

        //Verify
        assertEquals("KVN", currencyDatabase.getCurrencyByCode("KVN").code);
    }

    @Test
    public void testCurrencyExists() throws Exception{
        //Setup
        currencyDatabase.addCurrency(currency0);

        //Exercise
        currencyDatabase.currencyExists("KVN");

        //Verify
        assertTrue(currencyDatabase.currencyExists("KVN"));
    }

    @Test
    public void testGetCurrencies() throws Exception{
        //Setup
        List<Currency> currencyDatabaseList = new ArrayList<Currency>();

        List<Currency> currs = new ArrayList<Currency>();
        currs.add(currency0);
        currs.add(currency1);

        //Exercise
        currencyDatabaseList = currencyDatabase.getCurrencies();

        //Verify
        assertEquals(currs.indexOf(0),currencyDatabaseList.indexOf(0));
        assertEquals(currs.indexOf(1),currencyDatabaseList.indexOf(1));
    }

    @Test
    public void testGetMajorCurrencies() throws Exception{
        //Setup
        List<Currency> majorCurrencyDatabaseList = new ArrayList <Currency>();

        List<Currency> majorCurrs = new ArrayList<Currency>();
        majorCurrs.add(currency0);
        majorCurrs.add(currency1);

        //Exercise
        majorCurrencyDatabaseList = currencyDatabase.getMajorCurrencies();

        //Verify
        assertEquals(majorCurrs.indexOf(0),majorCurrencyDatabaseList.indexOf(0));
        assertEquals(majorCurrs.indexOf(1),majorCurrencyDatabaseList.indexOf(1));
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

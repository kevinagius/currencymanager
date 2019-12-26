package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;

public class ExchangeRateTest {

    ExchangeRate exchangeRate;
    Currency sourceCurrency = new Currency("CLY","Clay Coins",true);
    Currency destinationCurrency = new Currency("DLE", "Dale Dollaz",true);

    @Before
    public void setup() throws Exception{
        exchangeRate = new ExchangeRate(sourceCurrency,destinationCurrency,0.2312);
    }

    @After
    public void teardown(){
        exchangeRate = null;
    }

    @Test
    public void testExchangeRate() throws Exception{
        //Setup

        //Exercise

        //Verify
        assertSame(sourceCurrency, exchangeRate.sourceCurrency);
        assertSame(destinationCurrency, exchangeRate.destinationCurrency);
        assertEquals(0.2312,exchangeRate.rate);
    }

    @Test
    public void testToString() throws Exception{
        //Setup and Exercise
        String scodeDcodeRate = exchangeRate.toString();

        //Verify

        //0.23 inputted instead of 0.2312 due to a format for the amount being present in the original method
        assertEquals("CLY 1 = DLE 0.23", scodeDcodeRate);

    }
}

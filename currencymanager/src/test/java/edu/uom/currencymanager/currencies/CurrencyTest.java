package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;



public class CurrencyTest {

    Currency curr;

    @Before
    public void setup() throws Exception {
        curr = new Currency("BEZ","Bezz Bucks",true);

    }

    @After
    public void teardown(){
        curr = null;
    }

    @Test
    public void testFromString() throws Exception {
        //Setup and Exercise
        Currency curr2 = Currency.fromString("SAM,Sam Bucks,yes");

        //Verify
        String code = curr2.code;
        String name = curr2.name;
        boolean major = curr2.major;

        assertEquals("SAM", code);
        assertEquals("Sam Bucks", name);
        assertEquals(true, major);
    }

    @Test
    public void testToString() throws Exception {
        //Setup and Exercise
        String codeName = curr.toString();

        //Verify
        assertEquals("BEZ - Bezz Bucks", codeName);
    }


}

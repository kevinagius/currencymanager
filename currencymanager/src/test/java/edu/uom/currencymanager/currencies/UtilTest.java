package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UtilTest {

    Util util;

    @Before
    public void setup() throws Exception{


    }

    @After
    public void teardown(){
        util = null;

    }

    @Test
    public void testFormatAmount(){
        //Setup
        util = new Util();

        //Exercise
        String decimalFormat = util.formatAmount(123456789.01);

        //Verify
        assertEquals("123,456,789.01", decimalFormat);

    }
}

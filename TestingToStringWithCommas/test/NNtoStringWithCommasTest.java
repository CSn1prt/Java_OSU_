import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {

    @Test
    public void test() {
        fail("Not yet implemented");
    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas1.toStringWithCommas(n);
    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest1() {

        // setting up variables

        NaturalNumber comma1 = new NaturalNumber2(1);
        String commaNumber1 = redirectToMethodUnderTest(comma1);

        String expected1 = "1";

        System.out.println(commaNumber1);

        // making sure variables meet expectations

        assertEquals(expected1, commaNumber1);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest2() {

        // setting up variables

        NaturalNumber comma2 = new NaturalNumber2(100);
        String commaNumber2 = redirectToMethodUnderTest(comma2);

        String expected2 = "100";

        System.out.println(commaNumber2);

        // making sure variables meet expectations

        assertEquals(expected2, commaNumber2);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest3() {

        // setting up variables

        NaturalNumber comma3 = new NaturalNumber2(99999999);
        String commaNumber3 = redirectToMethodUnderTest(comma3);

        String expected3 = "99,999,999";

        System.out.println(commaNumber3);

        // making sure variables meet expectations

        assertEquals(expected3, commaNumber3);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest4() {

        // setting up variables

        NaturalNumber comma4 = new NaturalNumber2(50000000);
        String commaNumber4 = redirectToMethodUnderTest(comma4);

        String expected4 = "50,000,000";

        System.out.println(commaNumber4);

        // making sure variables meet expectations

        assertEquals(expected4, commaNumber4);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest5() {

        // setting up variables

        NaturalNumber comma5 = new NaturalNumber2(123456789);
        String commaNumber5 = redirectToMethodUnderTest(comma5);

        String expected5 = "123,456,789";

        System.out.println(commaNumber5);

        // making sure variables meet expectations

        assertEquals(expected5, commaNumber5);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest6() {

        // setting up variables

        NaturalNumber comma6 = new NaturalNumber2(429496729);
        String commaNumber6 = redirectToMethodUnderTest(comma6);

        String expected6 = "429,496,729";

        System.out.println(commaNumber6);

        // making sure variables meet expectations

        assertEquals(expected6, commaNumber6);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest7() {

        // setting up variables

        NaturalNumber comma7 = new NaturalNumber2(1024);
        String commaNumber7 = redirectToMethodUnderTest(comma7);

        String expected7 = "1,024";

        System.out.println(commaNumber7);

        // making sure variables meet expectations

        assertEquals(expected7, commaNumber7);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest8() {

        // setting up variables

        NaturalNumber comma8 = new NaturalNumber2(33333);
        String commaNumber8 = redirectToMethodUnderTest(comma8);

        String expected8 = "33,333";

        System.out.println(commaNumber8);

        // making sure variables meet expectations

        assertEquals(expected8, commaNumber8);

    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest9() {

        // setting up variables

        NaturalNumber comma9 = new NaturalNumber2(98765);
        String commaNumber9 = redirectToMethodUnderTest(comma9);

        String expected1 = "98,765";

        System.out.println(commaNumber9);

        // making sure variables meet expectations

        assertEquals(expected1, commaNumber9);

    }

}

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Seunghyun N
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_24_30() {
        NaturalNumber n = new NaturalNumber2(24);
        NaturalNumber nExpected = new NaturalNumber2(6);
        NaturalNumber m = new NaturalNumber2(30);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    //Additional Test Cases

    @Test
    public void testReduceToGCD_816_2260() {
        NaturalNumber n = new NaturalNumber2(816);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber m = new NaturalNumber2(2260);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    //Additional Test Cases
    @Test
    public void testIsEven_18() {
        NaturalNumber n = new NaturalNumber2(18);
        NaturalNumber nExpected = new NaturalNumber2(18);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    //Additional Test Cases

    /*
     * Test of isPrime1
     */

    @Test
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime1(n));
    }
    //boundary case

    @Test
    public void testIsPrime1_35() {
        NaturalNumber n = new NaturalNumber2(35);
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isPrime1(n));
    }
    //routine case

    @Test
    public void testIsPrime1_202471() {
        NaturalNumber n = new NaturalNumber2(202471);
        boolean wrong = true;
        assertEquals(wrong, CryptoUtilities.isPrime1(n));
    }
    //Large number case

    /*
     * Test of isPrime2
     */
    @Test
    // boundary
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime2(n));
    }

    @Test
    // routine
    public void testIsPrime2_27() {
        NaturalNumber n = new NaturalNumber2(12);
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isPrime2(n));
    }

    @Test
    // large routine
    public void testIsPrime2_2788927909() {
        NaturalNumber n = new NaturalNumber2("2788927909");
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime2(n));
    }

    /*
     * Test of isWitnessToCompositeness
     */
    @Test

    public void testIsWitnessToCompositeness_2_30() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(30);
        assertEquals("3", w.toString());
        assertEquals("30", n.toString());
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isWitnessToCompositeness(w, n));
    }
    //boundary case

    @Test
    public void testIsWitnessToCompositeness_12_75() {
        NaturalNumber w = new NaturalNumber2(12);
        NaturalNumber n = new NaturalNumber2(75);
        assertEquals("12", w.toString());
        assertEquals("75", n.toString());
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isWitnessToCompositeness(w, n));
    }
    //routine case

    @Test
    public void testIsWitnessToCompositeness_30_990() {
        NaturalNumber w = new NaturalNumber2(30);
        NaturalNumber n = new NaturalNumber2(990);
        assertEquals("30", w.toString());
        assertEquals("990", n.toString());
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isWitnessToCompositeness(w, n));
    }
    //Large number case

    /*
     * Test of generateNextLikelyNumber
     */
    @Test
    public void testGenerateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("5", n.toString());
    }
    //boundary case

    public void testGenerateNextLikelyPrime_18() {
        NaturalNumber n = new NaturalNumber2(19);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("19", n.toString());
    }
    //routine case

    public void testGenerateNextLikelyPrime_2788927909() {
        NaturalNumber n = new NaturalNumber2("2788927909");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("2788927909", n.toString());
    }
    //large number case

}
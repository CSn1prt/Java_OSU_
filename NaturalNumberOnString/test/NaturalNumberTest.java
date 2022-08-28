import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author nam.197 & others.
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     *
     * test case (constructors) test case with blank.
     */
    @Test
    public void TestWithBlank() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber test2 = this.constructorRef();
        assertEquals(test, test2);
    }

    /**
     *
     * test case (constructors) test case with blank and zero.
     */
    @Test
    public void TestWithBlankandZero() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber test2 = this.constructorRef();
        assertEquals(test, test2);
    }

    /**
     *
     * test case (constructors) test case with blank and zero.
     */
    @Test
    public void TestWithZeroandBlank() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber test2 = this.constructorRef(0);
        assertEquals(test, test2);
    }

    @Test
    public void TestWithZeroandBlank2() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber test2 = this.constructorRef(0);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with number in it.
     */
    @Test
    public void TestWithIntNumber() {
        NaturalNumber test = this.constructorTest(123);
        NaturalNumber test2 = this.constructorRef(123);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with max value.
     */
    @Test
    public void TestMaxInt() {
        NaturalNumber test = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber test2 = this.constructorRef(Integer.MAX_VALUE);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with number 0 in it.
     */
    @Test
    public void TestWithnumber0() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber test2 = this.constructorRef(0);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with String 0 in it.
     */
    @Test
    public void TestWithString0() {
        NaturalNumber test = this.constructorTest("0");
        NaturalNumber test2 = this.constructorRef("0");
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with String number in it.
     */
    @Test
    public void TestWithStringnumber() {
        NaturalNumber test = this.constructorTest("123");
        NaturalNumber test2 = this.constructorRef("123");
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with big string in it.
     */
    @Test
    public void TestBigStringNumber() {
        NaturalNumber test = this.constructorTest("1231231231231231321");
        NaturalNumber test2 = this.constructorRef("1231231231231231321");
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with nauturalNumber in it.
     */
    @Test
    public void TestNaturalNumber() {
        NaturalNumber a = new NaturalNumber1L(123);
        NaturalNumber b = new NaturalNumber1L(123);
        NaturalNumber test = this.constructorTest(a);
        NaturalNumber test2 = this.constructorRef(b);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with nauturalNumber nothing in it.
     */
    @Test
    public void TestNaturalNumberWithBlank() {
        NaturalNumber a = new NaturalNumber1L();
        NaturalNumber b = new NaturalNumber1L();
        NaturalNumber test = this.constructorTest(a);
        NaturalNumber test2 = this.constructorRef(b);
        assertEquals(test, test2);
    }

    /**
     * test case (constructors) test case with nauturalNumber nothing in it.
     */
    @Test
    public void TestNaturalNumberWithZero() {
        NaturalNumber a = new NaturalNumber1L(0);
        NaturalNumber b = new NaturalNumber1L(0);
        NaturalNumber test = this.constructorTest(a);
        NaturalNumber test2 = this.constructorRef(b);
        assertEquals(test, test2);
    }

    /**
     * test case (MultiplyBy10) test case
     */
    @Test
    public void TestMultiGeneral() {
        NaturalNumber test = this.constructorTest(123);
        NaturalNumber test2 = this.constructorRef(1235);
        test.multiplyBy10(5);
        assertEquals(test, test2);
    }

    /**
     * test case (MultiplyBy10) test case.
     */
    @Test
    public void TestMultiZero() {
        NaturalNumber test = this.constructorTest(0);
        NaturalNumber test2 = this.constructorRef(5);
        test.multiplyBy10(5);
        assertEquals(test, test2);
    }

    /**
     * test case (MultiplyBy10) blank.
     */
    @Test
    public void TestMultiBlank() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber test2 = this.constructorRef();
        test.multiplyBy10(0);
        assertEquals(test, test2);
    }

    /**
     * test case (MultiplyBy10) int k with 0.
     */
    @Test
    public void TestMultiByIntZero() {
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber test2 = this.constructorRef(50);
        test.multiplyBy10(0);
        assertEquals(test, test2);
    }

    /**
     * test case (MultiplyBy10) max value.
     */
    @Test
    public void TestMaxNumberMultiply() {
        NaturalNumber test = this.constructorTest("" + Integer.MAX_VALUE);
        NaturalNumber test2 = this.constructorRef("21474836475");
        test.multiplyBy10(5);
        assertEquals(test, test2);
    }

    /**
     * test case (divideby10) test.
     */
    @Test
    public void TestDivideGeneral() {
        NaturalNumber test = this.constructorTest(123);
        NaturalNumber test2 = this.constructorRef(12);
        int number = test.divideBy10();
        assertEquals(test, test2);
        assertEquals(number, 3);
    }

    /**
     * test case (divideby10) test with one digit.
     */
    @Test
    public void TestDivideOneDigit() {
        NaturalNumber test = this.constructorTest(5);
        NaturalNumber test2 = this.constructorRef();
        int number = test.divideBy10();
        assertEquals(test, test2);
        assertEquals(number, 5);
    }

    /**
     * test case (divideby10) test with blank.
     */
    @Test
    public void TestDivideWithBlank() {
        NaturalNumber test = this.constructorTest();
        NaturalNumber test2 = this.constructorRef();
        int number = test.divideBy10();
        assertEquals(test, test2);
        assertEquals(number, 0);
    }

    /**
     * test case (iszero) test with blank.
     */
    @Test
    public void TestIszeroWithBlank() {
        NaturalNumber test = this.constructorTest();

        boolean check = test.isZero();
        boolean yes = true;
        assertEquals(check, yes);
    }

    /**
     * test case (iszero) test with zero.
     */
    @Test
    public void TestIszeroWithZero() {
        NaturalNumber test = this.constructorTest(0);

        boolean check = test.isZero();
        boolean yes = true;
        assertEquals(check, yes);
    }

    /**
     * test case (iszero) test with not 0.
     */
    @Test
    public void TestIszeroWithNumber() {

        NaturalNumber test = this.constructorTest(5);

        boolean check = test.isZero();
        boolean yes = false;
        assertEquals(check, yes);
    }
    // TODO - add test cases for four constructors, multiplyBy10,
    //divideBy10, isZero

}

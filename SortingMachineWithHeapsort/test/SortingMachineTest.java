import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 *
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 *
 * kernel methods.
 *
 *
 *
 * @Author Seunghyun Nam, Group17
 *
 *
 *
 */

public abstract class SortingMachineTest {

    /**
     *
     * Invokes the appropriate {@code SortingMachine} constructor for the
     *
     * implementation under test and returns the result.
     *
     *
     *
     * @param order
     *
     *            the {@code Comparator} defining the order for {@code String}
     *
     * @return the new {@code SortingMachine}
     *
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     *
     * @ensures constructorTest = (true, order, {})
     *
     */

    protected abstract SortingMachine<String> constructorTest(

            Comparator<String> order);

    /**
     *
     * Invokes the appropriate {@code SortingMachine} constructor for the
     *
     * reference implementation and returns the result.
     *
     *
     *
     * @param order
     *
     *            the {@code Comparator} defining the order for {@code String}
     *
     * @return the new {@code SortingMachine}
     *
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     *
     * @ensures constructorRef = (true, order, {})
     *
     */

    protected abstract SortingMachine<String> constructorRef(

            Comparator<String> order);

    /**
     *
     *
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     *
     * implementation under test type with the given entries and mode.
     *
     *
     *
     * @param order
     *
     *            the {@code Comparator} defining the order for {@code String}
     *
     * @param insertionMode
     *
     *            flag indicating the machine mode
     *
     * @param args
     *
     *            the entries for the {@code SortingMachine}
     *
     * @return the constructed {@code SortingMachine}
     *
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     *
     * @ensures <pre>
    
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
    
     * </pre>
     *
     */

    private SortingMachine<String> createFromArgsTest(Comparator<String> order,

            boolean insertionMode, String... args) {

        SortingMachine<String> sm = this.constructorTest(order);

        for (int i = 0; i < args.length; i++) {

            sm.add(args[i]);

        }

        if (!insertionMode) {

            sm.changeToExtractionMode();

        }

        return sm;

    }

    /**
     *
     *
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     *
     * implementation type with the given entries and mode.
     *
     *
     *
     * @param order
     *
     *            the {@code Comparator} defining the order for {@code String}
     *
     * @param insertionMode
     *
     *            flag indicating the machine mode
     *
     * @param args
     *
     *            the entries for the {@code SortingMachine}
     *
     * @return the constructed {@code SortingMachine}
     *
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     *
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     *
     */

    private SortingMachine<String> createFromArgsRef(Comparator<String> order,

            boolean insertionMode, String... args) {

        SortingMachine<String> sm = this.constructorRef(order);

        for (int i = 0; i < args.length; i++) {

            sm.add(args[i]);

        }

        if (!insertionMode) {

            sm.changeToExtractionMode();

        }

        return sm;

    }

    /**
     *
     * Comparator<String> implementation to be used in all test cases. Compare
     *
     * {@code String}s in lexicographic order.
     *
     */

    private static class StringLT implements Comparator<String> {

        @Override

        public int compare(String s1, String s2) {

            return s1.compareToIgnoreCase(s2);

        }

    }

    /**
     *
     * Comparator instance to be used in all test cases.
     *
     */

    private static final StringLT ORDER = new StringLT();

    /*
     *
     * Sample test cases.
     *
     */

    /**
     * Testing constructor.
     */
    @Test

    public final void testConstructor() {

        SortingMachine<String> m = this.constructorTest(ORDER);

        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        assertEquals(mExpected, m);

    }

    /**
     * Testing add().
     */
    @Test

    public final void testAddToEmpty() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, true,

                "aaa");

        test.add("aaa");

        assertEquals(test, test2);

    }

    // Below is for testing isInInsertionMode, order, and size

    /**
     * Testing add().
     */
    @Test

    public final void testAddToOneExist() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "bravo");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, true,

                "alpha", "bravo");

        test.add("alpha");

        assertEquals(test, test2);

    }

    /**
     * Testing adding several methods.
     */
    @Test

    public final void testAddTwoElements() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "alpha",

                "bravo");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, true,

                "charlie", "alpha", "bravo");

        test.add("charlie");

        assertEquals(test, test2);

    }

    /**
     * Testing remove().
     */
    @Test

    public final void removeFromNormal() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,

                "alpha", "bravo");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, false,

                "bravo");

        String testing = test.removeFirst();

        assertEquals("alpha", testing);

        assertEquals(test, test2);

    }

    /**
     * Testing remove().
     */
    @Test

    public final void removeFirstOneElementRemaining() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,

                "alpha");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, false

        );

        String testing = test.removeFirst();

        assertEquals("alpha", testing);

        assertEquals(test, test2);

    }

    /**
     * Testing remove()r.
     */
    @Test

    public final void removeFirstTwoElementsRemaining() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,

                "blue", "green", "red");

        SortingMachine<String> expected = this.createFromArgsRef(ORDER, false,

                "green", "red");

        String removed = test.removeFirst();

        assertEquals("blue", removed);

        assertEquals(expected, test);

    }

    /**
     * Testing Insertion Mode.
     */
    @Test

    public final void isInInsertionModeTrue() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        boolean check = test.isInInsertionMode();

        assertEquals(true, check);

    }

    /**
     * Testing Insertion Mode.
     */
    @Test

    public final void isInInsertionModeFalse() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false);

        boolean check = test.isInInsertionMode();

        assertEquals(false, check);

    }

    /**
     * Testing Insertion Mode.
     */
    @Test

    public final void isInInsertionModeTrueWihtOne() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "bravo");

        boolean check = test.isInInsertionMode();

        assertEquals(true, check);

    }

    /**
     * Testing Insertion Mode.
     */
    @Test

    public final void isInInsertionModeFalseWithOne() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,

                "bravo");

        boolean check = test.isInInsertionMode();

        assertEquals(false, check);

    }

    /**
     * Testing if elements are in order.
     */
    @Test

    public final void order() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "alpha", "bravo");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, true,

                "alpha", "bravo");

        assertEquals(test.order(), test2.order());

    }

    /**
     * Testing if elements are in order, when in extraction mode.
     */
    @Test

    public final void orderInExtractionMode() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "alpha", "bravo");

        SortingMachine<String> test2 = this.createFromArgsRef(ORDER, true,

                "alpha", "bravo");
        test.changeToExtractionMode();

        assertEquals(test.order(), test2.order());

    }

    /**
     * Testing Extraction Mode.
     */
    @Test

    public final void testChnagetoExtractioWithTwo() {

        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "alpha");

        m.changeToExtractionMode();
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * Testing Extraction Mode.
     */
    @Test
    public final void testChangeToExtractionWithOne() {
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "red");
        s.changeToExtractionMode();
        assertEquals(false, s.isInInsertionMode());
    }

    /**
     * Testing when the size is 0.
     */
    @Test

    public final void sizeZero() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        int size = test.size();

        assertEquals(0, size);

    }

    /**
     * Testing with empty, in extraction mode.
     */
    @Test

    public final void sizeZeroExtractionMode() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);
        test.changeToExtractionMode();
        int size = test.size();

        assertEquals(0, size);

    }

    /**
     * Testing when the size is 1.
     */
    @Test
    public final void sizeOne() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "bravo");

        int size = test.size();

        assertEquals(1, size);

    }

    /**
     * Testing when the size is 2.
     */
    @Test

    public final void sizeTwo() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "alpha", "bravo");

        int size = test.size();

        assertEquals(2, size);

    }

    /**
     * Testing when non-empty in extraction mode.
     */
    @Test

    public final void sizeExt() {

        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,

                "alpha", "bravo");

        test.changeToExtractionMode();

        int size = test.size();

        assertEquals(2, size);

    }

}

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Seunghyun Nam,Group 17
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Testing constructor.
     */
    @Test
    public final void testSet3aConstructor() {
        Set<String> testSet = this.constructorTest();
        Set<String> refSet = this.constructorRef();

        assertEquals(refSet, testSet);
    }

    /**
     * Testing addZero().
     */
    @Test
    public final void testAddZero() {
        Set<String> test = this.createFromArgsTest();
        Set<String> test2 = this.createFromArgsRef("0");

        test.add("0");

        assertEquals(test, test2);
    }

    /**
     * Testing add().
     */
    @Test
    public final void testAddtwoValue() {
        Set<String> test = this.createFromArgsTest();
        Set<String> test2 = this.createFromArgsRef("0", "1");

        test.add("0");
        test.add("1");

        assertEquals(test, test2);

    }

    /**
     * Testing sets with identical elements (but value created in different
     * order).
     *
     */
    @Test
    public final void testDifferentValue() {
        Set<String> test = this.createFromArgsTest("0", "100", "101");
        Set<String> test2 = this.createFromArgsRef("101", "100", "0");

        assertEquals(test, test2);

    }

    /**
     * Testing add() (with multiple values).
     */
    @Test
    public final void testDifferentValuetwo() {
        Set<String> test = this.createFromArgsTest();
        Set<String> test2 = this.createFromArgsRef();

        test.add("5");
        test.add("15");
        test.add("20");
        test2.add("20");
        test2.add("5");
        test2.add("15");

        assertEquals(test, test2);

    }

    /**
     * Testing contains().
     */
    @Test
    public final void testContiantwo() {
        Set<String> test = this.createFromArgsTest("100", "1");

        boolean check = test.contains("0");

        assertEquals(check, false);

    }

    /**
     * Testing contains().
     */
    @Test
    public final void testContiantwotrue() {
        Set<String> test = this.createFromArgsTest("100", "1");

        boolean check = test.contains("100");

        assertEquals(check, true);

    }

    /**
     * Test Cases For remove().
     */

    @Test
    public final void testRemoveOne() {
        Set<String> test = this.createFromArgsTest("1");
        Set<String> test2 = this.createFromArgsTest();

        test.remove("1");

        assertEquals(test, test2);
    }

    /**
     * Test cases for checking if .remove() returns proper output.
     */
    @Test
    public final void testRemoveOneFromtwo() {
        Set<String> test = this.createFromArgsTest("1", "5");
        Set<String> test2 = this.createFromArgsRef("5");

        String check = test.remove("1");

        assertEquals(check, "1");
        assertEquals(test, test2);
    }

    /**
     * Test cases for remove().
     */
    @Test
    public final void testRemoveTwice() {
        Set<String> test = this.createFromArgsTest("1", "2");
        Set<String> test2 = this.createFromArgsTest();

        test.remove("1");
        test.remove("2");

        assertEquals(test, test2);
    }

    /**
     * Test cases for removeAny().
     */
    @Test
    public final void testRemoveany() {
        Set<String> test = this.createFromArgsTest("1", "2");
        Set<String> test2 = this.createFromArgsTest("1", "2");

        String number = test.removeAny();
        test2.remove(number);

        assertEquals(test, test2);
    }

    /**
     * Test case for checking if removeAny() returns proper output.
     */
    @Test
    public final void testRemoveanyFromOne() {
        Set<String> test = this.createFromArgsTest("1");
        Set<String> test2 = this.createFromArgsRef();

        String number = test.removeAny();

        assertEquals(number, "1");
        assertEquals(test, test2);
    }

    /**
     * Test case for testSize().
     */
    @Test
    public final void testSize() {
        Set<String> test = this.createFromArgsTest("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        int number = test.size();

        assertEquals(number, 10);
    }

    /**
     * Test cases for testSize().
     *
     */
    @Test
    public final void testSizeZero() {
        Set<String> test = this.createFromArgsTest();

        int number = test.size();

        assertEquals(number, 0);
    }

    /**
     * Test case for testSize().
     */
    @Test
    public final void testRemoveSmallest() {
        Set<String> test = this.createFromArgsTest("0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9");

        String template = "0";
        String response = test.removeAny();

        assertEquals(template, response);
    }

}
